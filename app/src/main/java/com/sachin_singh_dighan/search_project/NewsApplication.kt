package com.sachin_singh_dighan.search_project

import android.app.Application
import com.sachin_singh_dighan.search_project.di.component.ApplicationComponent
import com.sachin_singh_dighan.search_project.di.component.DaggerApplicationComponent
import com.sachin_singh_dighan.search_project.di.module.ApplicationModule

class NewsApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
        applicationComponent.inject(this@NewsApplication)
    }
}