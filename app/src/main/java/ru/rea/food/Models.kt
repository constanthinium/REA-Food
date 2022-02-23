package ru.rea.food

import com.google.gson.annotations.SerializedName

data class Account(
    val firstname: String? = null,
    val email: String,
    val password: String,
    val deviceId: String
)

data class Profile(
    val firstname: String,
    val email: String
)

data class AccountResponse(
    @SerializedName("data")
    val token: String,
    val errors: Errors
)

data class Errors(
    val firstname: String?,
    val email: String,
    val password: String
)

data class Place(
    val id: Int,
    @SerializedName("place_name")
    val name: String,
    @SerializedName("place_photo")
    val photoUrl: String,
    @SerializedName("place_open")
    val openTime: String,
    @SerializedName("place_close")
    val closeTime: String,
    @SerializedName("operating_mode")
    val operational: Boolean,
)

data class Category(
    val id: Int,
    @SerializedName("category_name")
    val name: String
)

data class Product(
    val id: Int,
    @SerializedName("name_product")
    val name: String,
    val price: Int,
    val photo: String,
    @SerializedName("text")
    val desc: String
)

data class Cart(
    @SerializedName("Items")
    val items: List<CartEntry>,
    @SerializedName("FinalAmount")
    val finalAmount: Int
)

data class CartEntry(
    val product: Product,
    val count: Int
)

data class Checkout(
    val url: String
)

data class OrderWrapper(val order: Order)

data class Order(
    val status: String,
    val products: List<ProductWrapper>
)

data class ProductWrapper(val product: Product)