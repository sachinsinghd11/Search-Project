package com.sachin_singh_dighan.search_project.data.repository

import com.sachin_singh_dighan.search_project.data.api.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val networkService: NetworkService) {

}