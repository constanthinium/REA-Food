package ru.rea.food.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.rea.food.R
import ru.rea.food.REAFoodService
import ru.rea.food.ui.topbar.TopAppBar
import ru.rea.food.vm.MainViewModel

@ExperimentalMaterialApi
@Composable
fun RestaurantsScreen(nav: NavController, viewModel: MainViewModel) {
    Column {
        TopAppBar(onClick = { nav.navigate("appMenu") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_group_399),
                    contentDescription = null
                )
            })
        LazyColumn {
            items(viewModel.places) {
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20.dp),
                    onClick = { nav.navigate("menu/${it.id}") }
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImage(
                            model = "${REAFoodService.baseUrl}/${it.photoUrl}",
                            contentDescription = null
                        )
                        Text(text = it.name, fontWeight = FontWeight.Bold)
                        Text(text = "Работает с ${it.openTime} до ${it.closeTime}")
                        if (it.operational) {
                            Text(text = "Работает", color = Color.Green)
                        } else {
                            Text(text = "Не работает", color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}