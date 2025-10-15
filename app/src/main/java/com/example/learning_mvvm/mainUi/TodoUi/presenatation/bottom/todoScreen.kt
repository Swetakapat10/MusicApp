package com.example.learning_mvvm.todo.presentation.todo

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.learning_mvvm.BuildConfig
import com.example.learning_mvvm.mainUi.TodoUi.data.model.Todo
import com.example.learning_mvvm.mainUi.TodoUi.presentation.top.CustomTopBar
import com.example.learning_mvvm.mainUi.TodoUi.utils.ApiResult
import com.example.learning_mvvm.mainUi.TodoUi.utils.component.ReusableCardComponent
import com.example.learning_mvvm.mainUi.TodoUi.utils.componet.SearchBar
import com.example.learning_mvvm.ui.theme.Learning_MVVMTheme

@Composable
fun TodoScreen(viewModel: TodoViewModel = hiltViewModel(), navController: NavController, searchQuery: String) {
    val state by viewModel.todoState.collectAsState()

    // Filter todos based on search query
    val filteredTodos = if (searchQuery.isEmpty()) {
        (state as? ApiResult.Success<List<Todo>>)?.data ?: emptyList()
    } else {
        (state as? ApiResult.Success<List<Todo>>)?.data?.filter {
            it.username.contains(searchQuery, ignoreCase = true) ||
                    it.description.contains(searchQuery, ignoreCase = true)
        } ?: emptyList()
    }

    when (state) {
        is ApiResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ApiResult.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {
                // Display filtered list of todos
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filteredTodos) { todo ->
                        ProductCard(todo = todo, navController = navController)
                    }
                }
            }
        }

        is ApiResult.Error -> {
            val error = state as ApiResult.Error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "Error: ${error.message} (Code: ${error.code ?: "Unknown"})",
                    color = Color.Red
                )
            }
        }
    }
}


@Composable
fun ProductCard(todo: Todo, navController: NavController) {
    Learning_MVVMTheme {
        ReusableCardComponent {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Display product image if available
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(todo.image)
                            .crossfade(true).build(),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 12.dp)
                    )
                    // Display product title (username in this case)
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "${todo.username}", style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Description: ${todo.description}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Price: $${todo.price}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            "Offer: ${todo.offer}%",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Delete button
                if (todo.id != -1) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            if (BuildConfig.FLAVOR == "paid") {
                                // Navigate to the Paid Content Screen
                                navController.navigate("paidContentScreen")
                            } else {
                                // Navigate to the Payment Screen if the user is not subscribed
                                navController.navigate("paymentScreen")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(" ▶ Play Now", color = MaterialTheme.colorScheme.onPrimary)
                    }
                } else {
                    Text(
                        "⚠️ ${todo.offer}",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}


