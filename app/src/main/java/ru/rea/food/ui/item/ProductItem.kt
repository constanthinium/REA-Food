package ru.rea.food.ui.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.rea.food.ImageFinder
import ru.rea.food.Product

@ExperimentalMaterialApi
@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    var image by remember { mutableStateOf<String?>(null) }
    ImageFinder.find(product.name) { image = it }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = onClick
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .width(192.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .padding(16.dp)
            )
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = product.desc,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}
