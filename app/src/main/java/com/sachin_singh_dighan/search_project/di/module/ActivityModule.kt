package com.sachin_singh_dighan.search_project.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sachin_singh_dighan.search_project.data.repository.SearchRepository
import com.sachin_singh_dighan.search_project.di.ActivityContext
import com.sachin_singh_dighan.search_project.ui.SearchAdapter
import com.sachin_singh_dighan.search_project.ui.SearchViewModel
import com.sachin_singh_dighan.search_project.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideSearchViewModel(searchRepository: SearchRepository): SearchViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(SearchViewModel::class) {
                SearchViewModel(searchRepository)
            })[SearchViewModel::class.java]
    }

    @Provides
    fun provideSearchAdapter()  = SearchAdapter(ArrayList())
}