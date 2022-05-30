package ru.rea.food.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rea.food.R
import ru.rea.food.ui.Button
import ru.rea.food.ui.CodeTextField
import ru.rea.food.ui.topbar.TopAccountBar

@Preview
@Composable
fun ResetPasswordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.content_padding)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAccountBar(text = stringResource(android.R.string.cancel))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(R.string.reset), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.sent))
        Spacer(modifier = Modifier.weight(.75f))
        Text(text = stringResource(R.string.enter))
        Spacer(modifier = Modifier.height(16.dp))
        CodeTextField()
        Spacer(modifier = Modifier.weight(.5f))
        Button(text = stringResource(R.string.reset)) { /*TODO*/ }
        Spacer(modifier = Modifier.height(16.dp))
    }
}