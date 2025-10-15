package com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learning_mvvm.mainUi.TodoUi.presentation.top.CustomTopBar
import com.example.learning_mvvm.mainUi.TodoUi.utils.componet.SearchBar

@Composable
fun SettingScreen(navController: NavController, searchQuery: String) {
    // Sample setting options (replace with actual data)
    val settings = remember {
        listOf(
            "General", "Notifications", "Privacy", "Security", "About"
        )
    }

    // Filter settings options based on search query
    val filteredSettings = if (searchQuery.isEmpty()) {
        settings
    } else {
        settings.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Display filtered settings options
        LazyColumn {
            items(filteredSettings) { setting ->
                Text(setting, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
