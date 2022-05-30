package ru.rea.food.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rea.food.OrderWrapper
import ru.rea.food.Product
import ru.rea.food.R
import ru.rea.food.REAFoodService
import ru.rea.food.ui.item.ProductItem

@ExperimentalMaterialApi
@Composable
fun RecentProductsScreen(token: String, onClick: (Product) -> Unit) {
    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Вы заказывали недавно",
            Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h5
        )
        var orders by remember { mutableStateOf<List<OrderWrapper>?>(null) }
        REAFoodService.instance.orders("Bearer $token")
            .enqueue(object : Callback<List<OrderWrapper>> {
                override fun onResponse(
                    call: Call<List<OrderWrapper>>,
                    response: Response<List<OrderWrapper>>
                ) {
                    orders = response.body()!!
                }

                override fun onFailure(call: Call<List<OrderWrapper>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        orders?.let {
            if (it.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Вы еще не делали заказов", style = MaterialTheme.typography.h5)
                }
            } else {
                val products = mutableListOf<Product>()
                val context = LocalContext.current
                it.forEach { it.order.products.forEach { products.add(it.product) } }
                LazyColumn {
                    items(products) {
                        if (it.desc == stringResource(id = R.string.missing)) {
                            ProductItem(product = it) {
                                Toast.makeText(context, it.desc, Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            ProductItem(product = it) { onClick(it) }
                        }
                    }
                }
            }
        }
    }
}