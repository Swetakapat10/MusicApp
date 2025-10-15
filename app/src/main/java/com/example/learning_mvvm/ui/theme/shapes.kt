package com.example.learning_mvvm.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Define custom shapes in your theme
val Shapes = Shapes(
    small = RoundedCornerShape(4.dp), // Small corner radius (for small elements like buttons)
    medium = RoundedCornerShape(8.dp), // Medium corner radius (for cards, etc.)
    large = RoundedCornerShape(16.dp) ,// Large corner radius (for large elements like dialogs)
    extraLarge =  RoundedCornerShape(25.dp), // Large corner radius (for large elements like dialogs)
)
