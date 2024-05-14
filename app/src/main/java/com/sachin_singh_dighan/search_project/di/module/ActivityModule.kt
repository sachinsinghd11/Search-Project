package com.sachin_singh_dighan.search_project.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.sachin_singh_dighan.search_project.data.repository.SearchRepository
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivityContext(): Context{
        return activity
    }
}