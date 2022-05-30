package ru.rea.food.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.rea.food.CartEntry
import ru.rea.food.ImageFinder
import ru.rea.food.REAFoodService
import ru.rea.food.vm.MenuViewModel

@Composable
fun CartItem(entry: CartEntry, viewModel: MenuViewModel) {
    var image by remember { mutableStateOf<String?>(null) }
    ImageFinder.find(entry.product.name) { image = it }

    Card(
        Modifier
            .height(96.dp)
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val product = entry.product
            image?.let {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    Modifier
                        .size(64.dp)
                        .padding(16.dp)
                )
            } ?: CircularProgressIndicator()
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = product.name, fontWeight = FontWeight.Bold)
                Text(
                    text = "₽" + product.price,
                    color = MaterialTheme.colors.primary
                )
            }
            val modifier =
                Modifier
                    .size(32.dp)
                    .background(MaterialTheme.colors.primary, CircleShape)
            Text(
                text = "-",
                modifier.clickable { viewModel.changeCount(product.id, entry.count - 1) },
                fontSize = 24.sp,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
            Text(text = entry.count.toString())
            Text(
                text = "+",
                modifier.clickable { viewModel.changeCount(product.id, entry.count + 1) },
                fontSize = 24.sp,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}