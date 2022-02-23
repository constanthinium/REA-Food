package ru.rea.food.ui.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TopBarButton(
    showIcon: Boolean = true,
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.surface)
    ) {
        if (showIcon) {
            icon()
        }
    }
}