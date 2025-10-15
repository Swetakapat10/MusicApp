package com.example.learning_mvvm.mainUi.Nav



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "homeScreen",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Todos : BottomNavItem(
        route = "albums",
        title = "Albums",
        icon = Icons.Default.List
    )

    object Payment : BottomNavItem(
        route = "myplaylist",
        title = "My playlist",
        icon = Icons.Default.Notifications
    )

    object Premium : BottomNavItem(
        route = "paidContentScreen",
        title = "Premium",
        icon = Icons.Default.Star
    )
    object Setting : BottomNavItem(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )

}

// List of bottom nav items
val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Todos,
    BottomNavItem.Payment,
    BottomNavItem.Premium,
    BottomNavItem.Setting
)