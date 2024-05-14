package com.sachin_singh_dighan.search_project.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<Article> = ArrayList(),
)
