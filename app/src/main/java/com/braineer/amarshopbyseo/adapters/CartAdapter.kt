package com.braineer.amarshopbyseo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.braineer.amarshopbyseo.databinding.CartItemBinding
import com.braineer.amarshopbyseo.databinding.ProductItemBinding
import com.braineer.amarshopbyseo.model.CartModel
import com.braineer.amarshopbyseo.model.ProductModel

class CartAdapter(val callback: (CartItemBinding, CartModel, Int)->Unit) : ListAdapter<CartModel, CartAdapter.ProductViewHolder>(
    ProductDiffUtil()
){

    class ProductViewHolder(val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: CartModel) {
            binding.cart = cart
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<CartModel>() {
        override fun areItemsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.bind(product)
        callback(holder.binding,product,position)


    }
}
