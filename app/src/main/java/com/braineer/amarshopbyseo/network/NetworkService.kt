package com.braineer.amarshopbyseo.network


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.braineer.amarshopbyseo.model.CartModel
import com.braineer.amarshopbyseo.model.ProductModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.*
import kotlin.collections.List


const val base_url = "https://fakestoreapi.com/"

val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ProductApi {
    @GET ()
    suspend fun getAllProduct(@Url endUrl: String): List<ProductModel>

    @GET ()
    suspend fun getAllCartProduct(@Url endUrl: String): List<CartModel>

    @GET ()
    suspend fun getProductById(@Url endUrl: String): ProductModel
}

object NetworkService {

    val productApi: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }
}
