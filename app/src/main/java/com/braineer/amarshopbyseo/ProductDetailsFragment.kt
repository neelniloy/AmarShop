package com.braineer.amarshopbyseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.braineer.amarshopbyseo.databinding.FragmentProductDetailsBinding
import com.braineer.amarshopbyseo.viewmodels.ProductViewModel
import com.bumptech.glide.Glide


class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailsBinding
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProductDetailsBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }

        val productId = arguments?.getLong("product_id")

        productId?.let { productViewModel.getProductById(it) }

        productViewModel.productLD.observe(viewLifecycleOwner){

            binding.cardProductDetails.visibility = View.VISIBLE
            binding.mProgressBar.visibility = View.GONE

            binding.productName.text = it.title
            binding.productPrice.text = "৳ ${it.price}"
            binding.productCategory.text = it.category
            binding.productDes.text = it.description

            it.image.let {
                Glide.with(requireActivity())
                    .load(it)
                    .placeholder(R.drawable.logo)
                    .into(binding.productImage)
            }
        }

        return binding.root
    }

}