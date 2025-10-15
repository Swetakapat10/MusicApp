package com.example.learning_mvvm.mainUi.TodoUi.presentation.top

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learning_mvvm.mainUi.TodoUi.utils.componet.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    navController: NavController, // Pass navController to track screen navigation
    title: String,
    showBackIcon: Boolean = true,
    showSettingsIcon: Boolean = true,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    var query by remember { mutableStateOf("") } // State to hold the search query
    var isSearchVisible by remember { mutableStateOf(false) } // State to toggle search bar visibility

    // Monitor navigation and reset the search bar visibility on tab change
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        // Hide search bar when navigating to a new screen
        isSearchVisible = false
        query = "" // Optionally clear the search query when navigating
    }

    // Animate the height of the TopAppBar based on the search bar visibility
    val topBarHeight by animateDpAsState(
        targetValue = if (isSearchVisible) 150.dp else 90.dp, // Increase height when search bar is visible
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 300) // Smooth transition
    )

    TopAppBar(
        modifier = Modifier.height(topBarHeight), // Apply the animated height here
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.tertiary),
        title = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AB", // Replace with initials
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.surface
                    )
                }

                // Conditionally display the search bar below the title
                if (isSearchVisible) {
                    Spacer(modifier = Modifier.height(8.dp))
                    SearchBar(
                        query = searchQuery,
                        onQueryChanged = onSearchQueryChanged,
                        onSearch = { /* Handle search logic if needed */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { /* Handle Back Navigation */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.surface)
                }
            }
        },
        actions = {
            // Show search icon if search bar is not already visible
            if (!isSearchVisible) {
                IconButton(onClick = { isSearchVisible = true }) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.surface)
                }
            }

            // Show settings icon if specified
            if (showSettingsIcon) {
                IconButton(onClick = { /* Handle Settings */ }) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.surface)
                }
            }
        },
    )
}
