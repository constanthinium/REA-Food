package ru.rea.food.ui.screen

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavController
import ru.rea.food.R
import ru.rea.food.ui.Button
import ru.rea.food.ui.topbar.TopBarButton
import ru.rea.food.vm.MainViewModel

@Composable
fun AppMenuScreen(
    nav: NavController,
    prefs: SharedPreferences,
    viewModel: MainViewModel,
    token: String
) {
    var dialogContent by remember { mutableStateOf<Pair<String, String>?>(null) }
    dialogContent?.let {
        AlertDialog(
            onDismissRequest = { dialogContent = null },
            confirmButton = {
                TextButton(onClick = { dialogContent = null }) {
                    Text(text = stringResource(android.R.string.ok))
                }
            },
            title = { Text(text = it.first) },
            text = { Text(text = it.second) }
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarButton(
            icon = { Icon(imageVector = Icons.Default.Close, contentDescription = null) },
            onClick = {
                nav.popBackStack()
            })
        var firstname by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        val profile = viewModel.profile(token)
        profile?.let {
            firstname = it.firstname
            email = it.email
        }
        Column {
            Text(text = firstname, fontWeight = FontWeight.Bold)
            Text(text = email)
        }
        listOf(
            R.drawable.ic_profile to "Мой профиль",
            R.drawable.ic_work to "Способ оплаты",
            R.drawable.ic_settings to "Настройки",
            R.drawable.ic_chat to "Помощь",
            R.drawable.ic_paper to "Политика конфеденциальности",
        ).forEachIndexed { i, it ->
            MenuItem(it, i != 2) {
                when (i) {
                    0 -> nav.navigate("profile")
                    1 -> dialogContent = Pair(
                        "Способ оплаты",
                        "В качестве способа оплаты используется Сбербанк Эквайринг"
                    )
                    3 -> dialogContent = Pair(
                        "Помощь",
                        "В случае возникновения вопросов обращайтесь к проектной документации приложения."
                    )
                    4 -> dialogContent = Pair(
                        "Политика конфеденциальности",
                        "Личные данные пользователя используются только в целях обработки поступающих заказов пользователя."
                    )
                }
            }
        }
        Spacer(modifier = Modifier)
        Button(text = stringResource(R.string.logout), fmw = false) {
            prefs.edit { remove("token") }
            nav.navigate("login") {
                popUpTo(0)
            }
        }
        Spacer(modifier = Modifier)
    }
}

@Composable
fun MenuItem(content: Pair<Int, String>, enabled: Boolean, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onBackground),
        enabled = enabled
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = content.first), contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = content.second)
        }
    }
}