package com.example.learning_mvvm.mainUi.TodoUi.presenatation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PaidVersionScreen(navController: NavController ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to the Paid Version!")
        Spacer(modifier = Modifier.height(16.dp))
        // Add paid content here
        Text("Here is the exclusive content for paid users.")
    }
}


