package ru.rea.food.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CodeTextField() {
    val shape = RoundedCornerShape(16.dp)
    Row(
        modifier = Modifier
            .shadow(56.dp, shape)
            .fillMaxWidth(.75f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val count = 4
        val digits = List(count) { remember { mutableStateOf<Int?>(null) } }
        repeat(count) { index ->
            val focusManager = LocalFocusManager.current
            BasicTextField(
                value = digits[index].value?.toString() ?: "",
                onValueChange = { text ->
                    text.firstOrNull()?.digitToIntOrNull().also {
                        digits[index].value = it
                        if (it != null) {
                            if (digits.all { digit -> digit.value != null }) {
                                focusManager.clearFocus()
                            } else {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        }
                    }
                },
                modifier = Modifier.size(56.dp),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                decorationBox = {
                    Box(
                        modifier = Modifier.background(
                            color = Color.White,
                            shape = shape
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        it()
                    }
                }
            )
        }
    }
}