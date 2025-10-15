package com.example.learning_mvvm.mainUi.TodoUi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("id") val id: Int,    // ID is a String in the API
    @SerialName("name") val username: String,  // API returns "name" instead of "username"
    val description: String,
    val price: Double,
    @SerialName("offer") val offer: String,  // API returns "offer" as String
    val image: String
)
