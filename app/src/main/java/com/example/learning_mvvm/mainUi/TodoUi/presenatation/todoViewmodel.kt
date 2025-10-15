package com.example.learning_mvvm.todo.presentation.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning_mvvm.mainUi.TodoUi.data.model.Todo
import com.example.learning_mvvm.mainUi.TodoUi.utils.ApiResult
import com.example.learning_mvvm.todo.data.remote.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todoState = MutableStateFlow<ApiResult<List<Todo>>>(ApiResult.Loading)
    val todoState: StateFlow<ApiResult<List<Todo>>> = _todoState

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _todoState.value = ApiResult.Loading
            val result = repository.fetchTodosWithRetry()
            _todoState.value = result
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun updateTodo(todo: Todo) {
        repository.updateTodo(todo)
        _todoState.value = ApiResult.Success(repository.getLocalTodos())
    }

}
