package ru.rea.food.ui.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(
    onClick: () -> Unit = {},
    onHelp: (() -> Unit)? = null,
    icon: @Composable () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarButton(onClick = onClick, icon = icon)
        onHelp?.let {
            TopBarButton(onClick = onHelp, icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            })
        } ?: TopBarButton(false)
    }
}