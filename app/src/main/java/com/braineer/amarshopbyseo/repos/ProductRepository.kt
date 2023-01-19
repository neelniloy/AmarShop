package com.braineer.amarshopbyseo.repos

import com.braineer.amarshopbyseo.model.ProductModel
import com.braineer.amarshopbyseo.network.NetworkService

class ProductRepository {
     suspend fun getAllProduct(): List<ProductModel> {

        return NetworkService.productApi.getAllProduct("products")
    }
}