package ru.rea.food.vm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.rea.food.Cart
import ru.rea.food.Category
import ru.rea.food.Product
import ru.rea.food.REAFoodService

private const val TAG = "MenuViewModel"

class MenuViewModel(val token: String) : ViewModel() {
    var categories by mutableStateOf(listOf<Category>())
    var products by mutableStateOf(listOf<Product>())

    fun categories(place: Int) {
        viewModelScope.launch {
            categories = REAFoodService.instance.categories(place)
        }
    }

    fun products(place: Int, category: Int) {
        viewModelScope.launch {
            products = if (category == 0) {
                REAFoodService.instance.products(place)
            } else {
                REAFoodService.instance.productsOfCategory(place, category)
            }
        }
    }

    fun changeCount(productId: Int, count: Int) {
        viewModelScope.launch {
            cart = REAFoodService.instance.changeCount("Bearer $token", productId, count)
        }
    }

    fun cart() {
        viewModelScope.launch {
            cart = REAFoodService.instance.cart("Bearer $token")
            Log.d(TAG, "cart: loaded")
        }
    }

    var cart by mutableStateOf<Cart?>(null)
}
