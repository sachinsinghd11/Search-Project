package com.sachin_singh_dighan.search_project.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin_singh_dighan.search_project.NewsApplication
import com.sachin_singh_dighan.search_project.data.model.Article
import com.sachin_singh_dighan.search_project.databinding.ActivitySearchBinding
import com.sachin_singh_dighan.search_project.di.component.DaggerActivityComponent
import com.sachin_singh_dighan.search_project.di.module.ActivityModule
import com.sachin_singh_dighan.search_project.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    companion object{
        const val TAG = "SearchActivity"
    }

    @Inject
    lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var adapter: SearchAdapter

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setObserver()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchNews(newText)
                return true
            }

        })
    }


    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE

                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            Log.e(TAG, it.message)
                            Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }

    private fun renderList(list: List<Article>) {
        adapter.updateData(list)
        adapter.notifyDataSetChanged()
    }
}