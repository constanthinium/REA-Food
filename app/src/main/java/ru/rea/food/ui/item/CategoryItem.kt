package ru.rea.food.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.rea.food.Category
import ru.rea.food.R

val icons = mapOf(
    "Выпечка" to R.drawable.ic_baseline_bakery_dining_24,
    "Горячие напитки" to R.drawable.ic_baseline_local_cafe_24,
    "Десерты" to R.drawable.ic_baseline_cake_24,
    "Пицца" to R.drawable.ic_baseline_local_pizza_24,
    "Салаты" to R.drawable.ic_baseline_dinner_dining_24,
    "Холодные напитки" to R.drawable.ic_baseline_local_drink_24
)

@Composable
fun CategoryItem(category: Category, selected: Boolean = false, onClick: (Int) -> Unit) {
    val colors = if (selected) {
        Color(0xFF3EC032) to Color(0x14A9E88B)
    } else {
        Color(0xFFF0CAC1) to Color(0x10F0CCC1)
    }

    Column(
        Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .clickable(onClick = { onClick(category.id) })
            .border(1.dp, colors.first, RoundedCornerShape(50))
            .padding(16.dp)
            .clip(RoundedCornerShape(48.dp))
            .background(colors.second)
            .padding(8.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(id = icons[category.name] ?: R.drawable.ic_baseline_fastfood_24),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = category.name)
    }
}