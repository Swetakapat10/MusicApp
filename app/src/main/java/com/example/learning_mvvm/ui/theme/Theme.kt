// ui.theme/Theme.kt
package com.example.learning_mvvm.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.learning_mvvm.ui.theme.Pink40
import com.example.learning_mvvm.ui.theme.Pink80
import com.example.learning_mvvm.ui.theme.Purple40
import com.example.learning_mvvm.ui.theme.Purple80
import com.example.learning_mvvm.ui.theme.PurpleGrey40
import com.example.learning_mvvm.ui.theme.PurpleGrey80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFCA28),  // Primary color (yellow)
    secondary = Color(0xfffba9a2), // Secondary color (soft pink)
    tertiary = Color(0xff5D4037),  // Tertiary color (brown)
    background = Color(0xFFFFF8E1),  // Background color (light beige)
    surface = Color(0xfffbf8ee),  // Surface color
    onPrimary = Color(0xff000000), // Text color on primary (black)
    onSecondary = Color(0xff616161),  // Text color on secondary (grey)
    onTertiary = Color.White,  // Text color on tertiary (white)
    onBackground = Color(0xff251814), // Text color on background (purple-ish)
    onSurface = Color(0xff5D4037) // Text color on surface (purple-ish)
)

@Composable
fun Learning_MVVMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}