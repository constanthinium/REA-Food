package ru.rea.food.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.rea.food.R

@Composable
fun MissingDialog(missing: String, onDismiss: () -> Unit) {
    AlertDialog(onDismiss, confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(text = stringResource(id = android.R.string.ok))
        }
    }, title = { Text(text = missing) })
}

@Preview
@Composable
fun MissingDialogPreview() {
    MissingDialog(stringResource(id = R.string.missing)) {}
}