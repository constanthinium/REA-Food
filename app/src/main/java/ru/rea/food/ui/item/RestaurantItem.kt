package ru.rea.food.ui.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.rea.food.ImageFinder
import ru.rea.food.Place

@ExperimentalMaterialApi
@Composable
fun RestaurantItem(place: Place, onClick: () -> Unit) {
    var image by remember { mutableStateOf<String?>(null) }
    ImageFinder.find(place.name) { image = it }

    Card(
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = onClick
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.size(192.dp)
            )
            Text(text = place.name, fontWeight = FontWeight.Bold)
            Text(text = "Работает с ${place.openTime} до ${place.closeTime}")
            if (place.operational) {
                Text(text = "Работает", color = Color.Green)
            } else {
                Text(text = "Не работает", color = Color.Red)
            }
        }
    }
}