package com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen

import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen.dataclas.NewsResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NewsRepository @Inject constructor(private val client: HttpClient) {

    // Suspended function to fetch and parse news
    suspend fun getNews(): NewsResponse {
        val response: HttpResponse = client.get("https://newsapi.org/v2/everything?q=tesla&from=2025-09-15&sortBy=publishedAt&apiKey=e12512f04b6443659fcf5a097e6bcc69")

        // Check if the response is successful and return the parsed body
        if (response.status == HttpStatusCode.OK) {
            val responseBody = response.bodyAsText()
            return Json.decodeFromString(responseBody)
        } else {
            throw Exception("Failed to fetch news")
        }
    }
}
