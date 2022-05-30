package ru.rea.food.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ru.rea.food.R
import ru.rea.food.ui.item.RestaurantItem
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
                RestaurantItem(it) {
                    nav.navigate("menu/${it.id}")
                }
            }
        }
    }
}