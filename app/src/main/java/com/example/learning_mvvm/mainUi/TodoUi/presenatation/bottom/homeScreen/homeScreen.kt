package com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.learning_mvvm.mainUi.TodoUi.data.model.Todo
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen.dataclas.Article
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen.dataclas.NewsViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    searchQuery: String,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    // Observe the news articles
    val newsArticles = newsViewModel.newsArticles

    // Sample list of todos (replace with actual data)
    val todos = remember {
        listOf(
            Todo(1, "John Doe", "Learn Compose", 100.00, "10%", "https://via.placeholder.com/150"),
            Todo(2, "Jane Smith", "Learn Kotlin", 200.00, "20%", "https://via.placeholder.com/150"),
            Todo(3, "Sam Green", "Build App", 300.00, "30%", "https://via.placeholder.com/150")
        )
    }

    // Filter todos based on search query
    val filteredTodos = if (searchQuery.isEmpty()) {
        todos
    } else {
        todos.filter {
            it.username.contains(searchQuery, ignoreCase = true) || it.description.contains(
                searchQuery,
                ignoreCase = true
            )
        }
    }

    Column(modifier = Modifier.systemBarsPadding()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Display filtered list of todos
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredTodos) { todo ->
                    TodoItem(todo)
                }

                items(newsArticles) { article ->
                    NewsItem(article)
                }
            }
        }
    }
}

@Composable
fun NewsItem(article: Article) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title ?: "No title", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = article.description ?: "No description",
                style = MaterialTheme.typography.bodyMedium
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(article.urlToImage)
                    .crossfade(true).build(),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 12.dp)
            )
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Username: ${todo.username}", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Description: ${todo.description}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Price: \$${todo.price}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Offer: ${todo.offer}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
