package com.example.learning_mvvm.mainUi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.learning_mvvm.ui.theme.Learning_MVVMTheme
import com.example.learning_mvvm.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(
                    Color.Black.toArgb(),
                    Color.Black.toArgb()
                )
            )
            Learning_MVVMTheme {

                // Initialize NavController here
                val navController = rememberNavController()
                // Set up NavGraph with NavController
                NavGraph(navController = navController) // Ensure NavGraph is passed to the correct composable
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Learning_MVVMTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController) // Make sure NavGraph is used in preview as well
    }
}
