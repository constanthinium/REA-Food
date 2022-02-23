package ru.rea.food.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Image(
    @DrawableRes painter: Int,
    modifier: Modifier = Modifier,
) {
    androidx.compose.foundation.Image(
        painter = painterResource(id = painter),
        contentDescription = null,
        modifier = modifier,
    )
}