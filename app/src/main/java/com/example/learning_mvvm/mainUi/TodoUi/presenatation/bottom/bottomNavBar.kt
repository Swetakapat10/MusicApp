package com.example.learning_mvvm.mainUi.TodoUi.presenatation.bottom

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learning_mvvm.mainUi.Nav.bottomNavItems

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    // Current Back Stack Entry for checking which screen is currently selected
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary, // Background color of the bottom bar
        contentColor = Color.White // Color of text/icons in the bottom bar
    ) {
        // Iterate through the bottomNavItems to create each item in the navigation bar
        bottomNavItems.forEach { item ->
            // Check if the current route matches the item route
            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = isSelected,
                onClick = {
                    // Handle navigation when an item is selected
                    navController.navigate(item.route) {
                        // Pop up to the start destination to avoid stacking the same screen
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary, // Color of selected item
                    selectedTextColor = MaterialTheme.colorScheme.primary, // Text color for selected item
                    unselectedIconColor = Color.White, // Color of unselected icon
                    unselectedTextColor = Color.White, // Text color for unselected item
                    indicatorColor =MaterialTheme.colorScheme.background, // Color for the indicator line
                )
            )
        }
    }
}
