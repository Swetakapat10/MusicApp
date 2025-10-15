package com.example.learning_mvvm.mainUi.TodoUi.utils.componet

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var searchQuery by mutableStateOf("") // Manage search query globally
        private set

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
}
