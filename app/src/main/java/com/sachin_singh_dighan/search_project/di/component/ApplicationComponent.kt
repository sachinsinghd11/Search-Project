package com.sachin_singh_dighan.search_project.di.component

import android.content.Context
import com.sachin_singh_dighan.search_project.NewsApplication
import com.sachin_singh_dighan.search_project.data.api.NetworkService
import com.sachin_singh_dighan.search_project.data.repository.SearchRepository
import com.sachin_singh_dighan.search_project.di.ApplicationContext
import com.sachin_singh_dighan.search_project.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getSearchRepository(): SearchRepository
}