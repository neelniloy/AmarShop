package com.braineer.amarshopbyseo.repos

import com.braineer.amarshopbyseo.model.CartModel
import com.braineer.amarshopbyseo.model.ProductModel
import com.braineer.amarshopbyseo.network.NetworkService

class ProductRepository {

     suspend fun getAllProduct(): List<ProductModel> {
        return NetworkService.productApi.getAllProduct("products")
    }

    suspend fun getAllCartProduct(): List<CartModel> {
        return NetworkService.productApi.getAllCartProduct("cart")
    }

    suspend fun getProductById(id: Long): ProductModel {
        return NetworkService.productApi.getProductById("products/${id}")
    }
}