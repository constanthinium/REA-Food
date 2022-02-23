package ru.rea.food.ui.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ru.rea.food.R

var address by mutableStateOf("")

@Composable
fun TopAppBar(
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarButton(onClick = onClick, icon = icon)
        var dialog by remember { mutableStateOf(false) }
        if (dialog) {
            AlertDialog(
                onDismissRequest = { dialog = false },
                confirmButton = {
                    TextButton(onClick = { dialog = false }) {
                        Text(text = "OK")
                    }
                },
                title = { Text(text = "Введите адрес") },
                text = {
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it })
                }
            )
        }
        Row(modifier = Modifier.clickable { dialog = true }) {
            Text(
                text = stringResource(R.string.delivery) + (if (address.isEmpty()) "" else "\n" + address),
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
        TopBarButton(false)
    }
}