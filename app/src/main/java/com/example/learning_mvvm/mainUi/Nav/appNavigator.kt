package com.example.learning_mvvm.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.homeScreen.HomeScreen
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.BottomNavigationBar
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.PaidVersionScreen
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.PaymentScreen
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.MyPlaylist
import com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom.SettingScreen
import com.example.learning_mvvm.mainUi.TodoUi.presentation.top.CustomTopBar
import com.example.learning_mvvm.todo.presentation.todo.TodoScreen

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learning_mvvm.mainUi.TodoUi.utils.componet.SearchViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val searchViewModel: SearchViewModel = viewModel() // Get the shared SearchViewModel

    Scaffold(
        topBar = {
            CustomTopBar(
                navController = navController,
                title = getCurrentScreenTitle(navController),
                showBackIcon = showBackIconForScreen(navController),
                showSettingsIcon = showSettingsIconForScreen(navController),
                searchQuery = searchViewModel.searchQuery, // Pass search query to top bar
                onSearchQueryChanged = { searchViewModel.updateSearchQuery(it) } // Handle search query change
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("homeScreen") {
                HomeScreen(navController = navController, searchQuery = searchViewModel.searchQuery)
            }
            composable("albums") {
                TodoScreen(navController = navController, searchQuery = searchViewModel.searchQuery)
            }
            composable("myplaylist") {
                MyPlaylist(navController = navController, searchQuery = searchViewModel.searchQuery)
            }
            composable("paidContentScreen") {
                PaidVersionScreen(navController = navController)
            }
            composable("setting") {
                SettingScreen(navController = navController,searchQuery = searchViewModel.searchQuery)
            }
            composable("paymentScreen") {
                PaymentScreen(navController = navController)
            }
        }
    }
}


// Get the dynamic title based on the current screen
@Composable
fun getCurrentScreenTitle(navController: NavController): String {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    return when (currentBackStackEntry?.destination?.route) {
        "homeScreen" -> "Home"
        "setting" -> "Settings"
        "albums" -> "Albums"
        "myplaylist" -> "My Playlist"
        "paidContentScreen" -> "Paid Content"
        "paymentScreen" -> "Payment"
        else -> "Unknown Screen" // Default title
    }
}

@Composable
fun showBackIconForScreen(navController: NavController): Boolean {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    return when (currentBackStackEntry?.destination?.route) {
        "homeScreen" -> false // No back button for Home Screen
        else -> false // Show back button for other screens
    }
}

@Composable
fun showSettingsIconForScreen(navController: NavController): Boolean {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    return when (currentBackStackEntry?.destination?.route) {
        "homeScreen" -> true // Show settings icon on Home Screen
        "albums"->true
        "myplaylist"->true
        "setting" -> false // Don't show settings icon on Setting Screen
        else -> false // Hide settings icon on other screens
    }
}
