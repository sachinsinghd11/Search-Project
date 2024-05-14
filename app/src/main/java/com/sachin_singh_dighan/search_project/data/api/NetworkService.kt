package com.sachin_singh_dighan.search_project.data.api

import com.sachin_singh_dighan.search_project.data.model.SearchResponse
import com.sachin_singh_dighan.search_project.utils.AppConstant
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    @Headers("X-Api-Key: ${AppConstant.API_KEY}")
    @GET("everything")
    suspend fun getNews(@Query("query") query: String): SearchResponse

}