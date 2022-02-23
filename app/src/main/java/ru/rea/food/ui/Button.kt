package ru.rea.food.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val gradientBrush = Brush.linearGradient(
    listOf(
        Color(0xFFF9881F),
        Color(0xFFFF774C)
    )
)

@Composable
fun Button(
    text: String,
    fmw: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues()
    ) {
        val modifier = if (fmw) Modifier.fillMaxWidth() else Modifier
        Box(
            modifier = modifier
                .height(56.dp)
                .background(gradientBrush)
                .padding(ButtonDefaults.ContentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = MaterialTheme.colors.onPrimary)
        }
    }
}