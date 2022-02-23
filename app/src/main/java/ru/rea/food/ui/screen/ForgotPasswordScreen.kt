package ru.rea.food.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.rea.food.R
import ru.rea.food.ui.AccountButtons
import ru.rea.food.ui.TextField
import ru.rea.food.ui.topbar.TopAccountBar

@Composable
fun ForgotPasswordScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.content_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAccountBar(text = stringResource(R.string.cancel))
        Spacer(Modifier.weight(1f))
        Text(text = stringResource(R.string.forgot), style = MaterialTheme.typography.h5)
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.request),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(64.dp))
        TextField(label = "Enter email address", title = "Email Address")
        Spacer(Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier)
            AccountButtons(
                onCreate = { /*TODO*/ },
                onLogin = onClick,
                text = stringResource(R.string.login_my),
                google = true,
            )
        }
    }
}