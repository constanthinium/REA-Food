package ru.rea.food.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.rea.food.Product
import ru.rea.food.ui.item.ProductItem
import ru.rea.food.vm.MenuViewModel

@ExperimentalMaterialApi
@Composable
fun SearchScreen(viewModel: MenuViewModel, onClick: (Product) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var search by remember { mutableStateOf("") }
        Text(text = "Поиск", Modifier.fillMaxWidth(), style = MaterialTheme.typography.h5)
        OutlinedTextField(value = search, onValueChange = { search = it })
        val products = viewModel.products.filter { it.name.contains(search, true) }
        if (products.isEmpty()) {
            Box(Modifier.fillMaxSize(), Alignment.Center)
            {
                Text(
                    text = "Похоже, ничего нет",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )
            }
        } else {
            LazyColumn {
                items(products) {
                    ProductItem(product = it) { onClick(it) }
                }
            }
        }
    }
}