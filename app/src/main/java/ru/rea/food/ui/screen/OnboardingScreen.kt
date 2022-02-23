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
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ru.rea.food.R
import ru.rea.food.ui.AccountButtons
import ru.rea.food.ui.Image
import ru.rea.food.ui.PagerIndicator
import ru.rea.food.ui.theme.Green
import ru.rea.food.ui.theme.LightGray
import ru.rea.food.ui.topbar.TopAccountBar

@ExperimentalPagerApi
@Composable
fun OnboardingScreen(nav: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.content_padding)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAccountBar(bold = true, text = stringResource(R.string.skip))
        val pagerState = rememberPagerState()
        val content = listOf(
            R.string.page_1 to R.drawable.page_1,
            R.string.page_2 to R.drawable.page_2,
            R.string.page_3 to R.drawable.page_3
        )
        HorizontalPager(
            count = content.size,
            state = pagerState,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = content[it].first),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(48.dp))
                Image(
                    painter = content[it].second,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(256.dp),
                )
            }
        }
        PagerIndicator(
            pagerState = pagerState,
            activeColor = Green,
            inactiveColor = LightGray,
        )
        AccountButtons(
            onCreate = { nav.navigate("create") },
            onLogin = { nav.navigate("login") },
            text = stringResource(R.string.login),
            google = false,
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}