package com.sachin_singh_dighan.search_project.di.module

import android.content.Context
import com.sachin_singh_dighan.search_project.NewsApplication
import com.sachin_singh_dighan.search_project.data.api.NetworkService
import com.sachin_singh_dighan.search_project.di.ApplicationContext
import com.sachin_singh_dighan.search_project.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build().create(NetworkService::class.java)
    }
}