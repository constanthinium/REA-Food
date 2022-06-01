package ru.rea.food

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

@Suppress("SpellCheckingInspection")
interface REAFoodService {
    @POST("register")
    fun signUp(@Body account: Account): Call<AccountResponse>

    @POST("login")
    fun logIn(@Body account: Account): Call<AccountResponse>

    @GET("places")
    suspend fun places(): List<Place>

    @GET("categorys/{placeId}")
    suspend fun categories(@Path("placeId") placeId: Int): List<Category>

    @GET("products/{placeId}")
    suspend fun products(@Path("placeId") placeId: Int): List<Product>

    @GET("products/{placeId}/{categoryId}")
    suspend fun productsOfCategory(
        @Path("placeId") placeId: Int,
        @Path("categoryId") categoryId: Int
    ): List<Product>

    @GET("profile")
    suspend fun profile(@Header("Authorization") token: String): Profile

    @GET("cart")
    suspend fun cart(@Header("Authorization") token: String): Cart

    @FormUrlEncoded
    @POST("cart")
    suspend fun cart(
        @Header("Authorization") token: String,
        @Field("productId") productId: Int,
        @Field("count") count: Int
    ): Cart

    @FormUrlEncoded
    @POST("changecount")
    suspend fun changeCount(
        @Header("Authorization") token: String,
        @Field("productId") productId: Int,
        @Field("count") count: Int
    ): Cart

    @FormUrlEncoded
    @POST("changeName")
    suspend fun changeName(
        @Header("Authorization") token: String,
        @Field("firstname") name: String
    ): AccountResponse

    @FormUrlEncoded
    @POST("changeEmail")
    suspend fun changeEmail(
        @Header("Authorization") token: String,
        @Field("email") email: String
    ): AccountResponse

    @FormUrlEncoded
    @POST("changePassword")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Field("password") password: String
    ): AccountResponse

    @FormUrlEncoded
    @POST("checkout")
    suspend fun checkout(
        @Header("Authentication") token: String,
        @Field("select_date") time: String
    ): Checkout

    @GET("orders")
    fun orders(@Header("Authorization") token: String) : Call<List<OrderWrapper>>

    @POST("clearCart")
    suspend fun clearCart(@Header("Authorization") token: String): Response<Void>

    companion object {
        val instance: REAFoodService = Retrofit.Builder()
            .baseUrl("https://eda.ucmpt.ru/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(REAFoodService::class.java)
    }
}