package ru.rea.food.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import ru.rea.food.ImageFinder
import ru.rea.food.Product
import ru.rea.food.REAFoodService
import ru.rea.food.ui.Button
import ru.rea.food.ui.gradientBrush
import ru.rea.food.ui.topbar.TopAppBar

@Composable
fun ProductScreen(
    product: Product,
    token: String,
    onBack: () -> Unit
) {
    var image by remember { mutableStateOf<String?>(null) }
    ImageFinder.find(product.name) { image = it }
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
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    )
                }
            }
            var count by remember { mutableStateOf(1) }
            CountCard(count) { count = it }
            Text(
                text = product.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = product.desc)
            val scope = rememberCoroutineScope()
            Button(text = "Add to cart") {
                scope.launch {
                    REAFoodService.instance.cart("Bearer $token", product.id, count)
                }
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