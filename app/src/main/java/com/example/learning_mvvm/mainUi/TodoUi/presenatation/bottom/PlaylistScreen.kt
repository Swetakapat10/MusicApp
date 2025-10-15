package com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learning_mvvm.mainUi.TodoUi.presentation.top.CustomTopBar


@Composable
fun MyPlaylist(navController: NavController, searchQuery: String) {
    // Sample playlist data (replace with actual data)
    val playlists = remember {
        listOf(
            "Playlist 1", "Playlist 2", "Playlist 3", "Cool Playlist", "My Playlist"
        )
    }

    // Filter playlists based on search query
    val filteredPlaylists = if (searchQuery.isEmpty()) {
        playlists
    } else {
        playlists.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Add SearchBar

        // Display filtered playlists
        LazyColumn {
            items(filteredPlaylists) { playlist ->
                Text(playlist, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}


