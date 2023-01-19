package com.braineer.amarshopbyseo.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("rating")
    val rating: Rating,) {

    data class Rating(
        @SerializedName("rate")
        val lat: Double,
        @SerializedName("count")
        val lon: Long
    )
}