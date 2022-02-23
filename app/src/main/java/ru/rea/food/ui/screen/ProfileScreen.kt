package ru.rea.food.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import ru.rea.food.R
import ru.rea.food.ui.Button
import ru.rea.food.ui.TextField
import ru.rea.food.vm.MainViewModel

@Composable
fun ProfileScreen(token: String, viewModel: MainViewModel, onBack: () -> Unit) {
    Column(
        Modifier
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = null
            )
        }
        Text(text = stringResource(R.string.profile), style = MaterialTheme.typography.h5)
        val profile = viewModel.profile!!
        var name by remember { mutableStateOf(profile.firstname) }
        TextField(
            label = "Name",
            text = name,
            onTextChange = { name = it },
            error = viewModel.errors?.firstname ?: ""
        )
        Button(text = stringResource(R.string.name)) {
            viewModel.changeName(token, name)
        }
        var email by remember { mutableStateOf(profile.email) }
        TextField(
            label = "Enter email",
            title = "Email Address",
            text = email,
            onTextChange = { email = it },
            error = viewModel.errors?.email ?: ""
        )
        Button(text = stringResource(R.string.change_email)) {
            viewModel.changeEmail(token, name)
        }
        var password by remember { mutableStateOf("") }
        TextField(
            label = "Enter password",
            title = "Password",
            text = password,
            onTextChange = { password = it },
            error = viewModel.errors?.password ?: "",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        var confirm by remember { mutableStateOf("") }
        var confirmError by remember { mutableStateOf("") }
        TextField(
            label = "Confirm Password",
            text = confirm,
            onTextChange = { confirm = it },
            error = confirmError,
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Button(text = stringResource(R.string.change_password)) {
            if (password != confirm) {
                confirmError = "Пароли не совпадают"
            } else {
                confirmError = ""
                viewModel.changePassword(token, password)
            }
        }
    }
}