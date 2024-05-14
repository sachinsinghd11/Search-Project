package com.sachin_singh_dighan.search_project.di.component

import com.sachin_singh_dighan.search_project.SearchActivity
import com.sachin_singh_dighan.search_project.di.ActivityScope
import com.sachin_singh_dighan.search_project.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: SearchActivity)
}