package com.braineer.amarshopbyseo.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braineer.amarshopbyseo.model.CartModel
import com.braineer.amarshopbyseo.model.ProductModel
import com.braineer.amarshopbyseo.repos.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel:ViewModel() {
    val repository = ProductRepository()
    val productListLD: MutableLiveData<List<ProductModel>> = MutableLiveData()
    val cartListLD: MutableLiveData<List<CartModel>> = MutableLiveData()
    val productLD: MutableLiveData<ProductModel> = MutableLiveData()

     fun getAllProduct(){

         viewModelScope.launch {
             try {
                 productListLD.value = repository.getAllProduct()

                 Log.e("productViewModel", repository.getAllProduct().toString())
             }catch (e: Exception) {
                 Log.e("productViewModel", e.localizedMessage)
             }
         }

    }

    fun getProductById(id: Long){

        viewModelScope.launch {
            try {
                productLD.value = repository.getProductById(id)
            }catch (e: Exception) {
                Log.e("productViewModel", e.localizedMessage)
            }
        }

    }

    fun getAllCartProduct(){

        viewModelScope.launch {
            try {
                cartListLD.value = repository.getAllCartProduct()

            }catch (e: Exception) {
                Log.e("productViewModel", e.localizedMessage)
            }
        }

    }


}
