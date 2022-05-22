package ru.rea.food.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.rea.food.Product
import ru.rea.food.REAFoodService
import ru.rea.food.ui.Button
import ru.rea.food.ui.gradientBrush
import ru.rea.food.ui.topbar.TopAppBar

@Composable
fun ProductScreen(
    product: Product?,
    token: String,
    onBack: () -> Unit
) {
    BackHandler { onBack() }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TopAppBar(onBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterHorizontally
        ) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.5f),
                shape = RoundedCornerShape(20.dp),
                elevation = 32.dp
            ) {
                AsyncImage(
                    model = "${REAFoodService.baseUrl}/${product!!.photo}",
                    contentDescription = null,
                    Modifier.padding(32.dp)
                )
            }
            var count by remember { mutableStateOf(1) }
            CountCard(count) { count = it }
            Text(
                text = product!!.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = product.desc)
            Button(text = "Add to cart") {
                GlobalScope.launch { REAFoodService.instance.cart("Bearer $token", product.id, count) }
                onBack()
            }
        }
    }
}

@Composable
fun CountCard(count: Int, onChange: (Int) -> Unit) {
    Card(shape = RoundedCornerShape(50)) {
        Row(
            Modifier
                .background(gradientBrush)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val color = MaterialTheme.colors.onPrimary
            Text(
                text = "-",
                color = color,
                modifier = Modifier
                    .clickable { if (count > 1) onChange(count - 1) }
                    .padding(8.dp)
            )
            Text(text = count.toString(), color = color, modifier = Modifier.padding(8.dp))
            Text(
                text = "+",
                color = color,
                modifier = Modifier
                    .clickable { onChange(count + 1) }
                    .padding(8.dp)
            )
        }
    }
}