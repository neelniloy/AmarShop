package com.braineer.amarshopbyseo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.braineer.amarshopbyseo.databinding.ProductItemBinding
import com.braineer.amarshopbyseo.model.ProductModel

class ProductAdapter() : ListAdapter<ProductModel, ProductAdapter.ProductViewHolder>(
    ProductDiffUtil()
){

    class ProductViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.product = product
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.bind(product)
        holder.itemView.setOnClickListener {

        }

    }
}
