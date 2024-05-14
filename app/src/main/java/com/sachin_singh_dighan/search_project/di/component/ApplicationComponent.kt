package com.sachin_singh_dighan.search_project.di.component

import com.sachin_singh_dighan.search_project.NewsApplication
import com.sachin_singh_dighan.search_project.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: NewsApplication)
}