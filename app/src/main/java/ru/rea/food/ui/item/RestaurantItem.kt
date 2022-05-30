package ru.rea.food.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.rea.food.Place
import ru.rea.food.REAFoodService

@ExperimentalMaterialApi
@Composable
fun RestaurantItem(it: Place, onClick: () -> Unit) {
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