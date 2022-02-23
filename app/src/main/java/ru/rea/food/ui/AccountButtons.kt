package ru.rea.food.ui

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.rea.food.R

@Composable
fun AccountButtons(
    onCreate: () -> Unit,
    onLogin: () -> Unit,
    text: String,
    google: Boolean
) {
    if (google) {
        GoogleButton()
    }
    Button(text = stringResource(R.string.create), onClick = onCreate)
    TextButton(
        onClick = onLogin,
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}