package ru.rea.food.ui.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import ru.rea.food.R
import ru.rea.food.ui.Image

@Composable
fun TopAccountBar(
    bold: Boolean = false,
    text: String
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = R.drawable.icon,
            modifier = Modifier.align(Alignment.Center)
        )
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = text,
                fontWeight = if (bold) FontWeight.Bold else null,
                textDecoration = if (!bold) TextDecoration.Underline else null
            )
        }
    }
}