package com.braineer.amarshopbyseo.model

import com.google.gson.annotations.SerializedName

data class CartModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("product")
    val product: ProductModel,
  ) {
    data class ProductsInfo(
        @SerializedName("productId")
        val productId: Long,
        @SerializedName("quantity")
        val quantity: Long
    )
}
