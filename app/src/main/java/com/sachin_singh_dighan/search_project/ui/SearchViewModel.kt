package com.sachin_singh_dighan.search_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin_singh_dighan.search_project.data.model.Article
import com.sachin_singh_dighan.search_project.data.repository.SearchRepository
import com.sachin_singh_dighan.search_project.utils.AppConstant
import com.sachin_singh_dighan.search_project.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    val query = MutableStateFlow("")

    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String) {
        query.value = searchQuery
    }

    private fun createNewsFlow() {
        viewModelScope.launch {
            query.debounce(AppConstant.DEBOUNCE_TIMEOUT).filter {
                if (it.isNotEmpty() && it.length >= AppConstant.MIN_SEARCH_CHAR) {
                    return@filter true
                } else {
                    _uiState.value = UiState.Success(emptyList())
                    return@filter false
                }
            }.distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest searchRepository.getNews(it)
                        .catch { e ->
                            //handle error properly
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    //handle response and empty response properly
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}