package ru.rea.food.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    text: String = "",
    onTextChange: (String) -> Unit = {},
    label: String,
    title: String = label,
    error: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    capitalize: Boolean = false
) {
    Column(horizontalAlignment = Alignment.Start) {
        Spacer(Modifier.height(12.dp))
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = error.ifEmpty { label }) },
            isError = error.isNotEmpty(),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                capitalization = if (capitalize) {
                    KeyboardCapitalization.Sentences
                } else KeyboardCapitalization.None,
                keyboardType = keyboardType
            ),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = Color(0xFFDFE2E5)
            )
        )
    }
}