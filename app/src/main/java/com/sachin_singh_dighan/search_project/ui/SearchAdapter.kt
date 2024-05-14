package com.sachin_singh_dighan.search_project.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin_singh_dighan.search_project.data.model.Article
import com.sachin_singh_dighan.search_project.databinding.SearchItemLayoutBinding

class SearchAdapter(
    private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<SearchAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context).load(article.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            SearchItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    fun updateData(list: List<Article>) {
        articleList.clear()
        articleList.addAll(list)
    }
}