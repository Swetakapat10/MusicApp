package com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen.dataclas


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    var newsArticles: List<Article> by mutableStateOf(emptyList())
        private set

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getNews()
                newsArticles = response.articles
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
