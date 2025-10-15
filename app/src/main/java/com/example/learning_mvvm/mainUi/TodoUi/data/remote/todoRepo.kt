package com.example.learning_mvvm.todo.data.remote

import android.os.Build
import android.util.Log
import com.example.learning_mvvm.mainUi.TodoUi.data.model.Todo
import com.example.learning_mvvm.mainUi.TodoUi.utils.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val client: HttpClient
) {
    private var localData = mutableListOf<Todo>()

    suspend fun fetchTodosWithRetry(): ApiResult<List<Todo>> {
        repeat(3) { attempt ->
            try {
                val response =
                    client.get("https://68edf2a8df2025af7801b152.mockapi.io/productinfo") {
                        // Optional headers, timeouts, etc.
                    }

                if (response.status.value == 200) {
                    // Ensure the response is parsed correctly
                    val data = Json.decodeFromString<List<Todo>>(response.bodyAsText())
                    localData = data.toMutableList() // Update local data
                    return ApiResult.Success(localData)
                } else {
                    return ApiResult.Error("Server Error", response.status.value)
                }
            } catch (e: Exception) {
                // Log the exception for debugging
                Log.e("TodoRepository", "Error fetching data: ${e.message}", e)
                delay(20_000) // Wait 20 seconds before retry
            }
        }

        // After 3 failed attempts, fallback to local data
        return ApiResult.Success(localData).also {
            localData.add(
                Todo(
                    id = 1,
                    username = "earbuds",
                    description = "DATA not found",
                    price = 10000.0,
                    offer = "10",
                    image = "https://picsum.photos/200/300?random=9"
                )
            )
        }
    }
        fun updateTodo(updated: Todo) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                localData.replaceAll { if (it.id == updated.id) updated else it }
            }
        }

        fun deleteTodo(todoId: Int) {
            localData.removeAll { false }
        }


        fun getLocalTodos(): List<Todo> {
            return localData
        }
}