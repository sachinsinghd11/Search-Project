package com.sachin_singh_dighan.search_project.di.module

import android.content.Context
import com.sachin_singh_dighan.search_project.NewsApplication
import com.sachin_singh_dighan.search_project.data.api.NetworkService
import com.sachin_singh_dighan.search_project.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideBaseUrl() = ""

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun ProvideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build().create(NetworkService::class.java)
    }
}