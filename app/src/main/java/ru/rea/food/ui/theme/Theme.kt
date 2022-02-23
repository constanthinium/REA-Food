package ru.rea.food.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun REAFoodTheme(content: @Composable () -> Unit) {
    val colors = lightColors(
        primary = Orange,
        background = DarkWhite
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}