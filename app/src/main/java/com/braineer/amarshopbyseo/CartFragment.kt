package com.braineer.amarshopbyseo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.braineer.amarshopbyseo.adapters.CartAdapter
import com.braineer.amarshopbyseo.adapters.ProductAdapter
import com.braineer.amarshopbyseo.databinding.FragmentCartBinding
import com.braineer.amarshopbyseo.model.ProductModel
import com.braineer.amarshopbyseo.viewmodels.ProductViewModel


class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCartBinding.inflate(inflater, container, false)

        val adapter = CartAdapter {binding,cart,position->



        }

        val glm = GridLayoutManager(requireActivity(), 2)
        binding.productRV.layoutManager = glm
        binding.productRV.adapter = adapter

        //Fetch
        productViewModel.getAllProduct()

        productViewModel.cartListLD.observe(viewLifecycleOwner) {cartList ->

            if (cartList.isEmpty()) {
                binding.mProgressBar.visibility = View.VISIBLE
            } else {
                binding.mProgressBar.visibility = View.GONE
                adapter.submitList(cartList)
            }

        }


        return binding.root
    }

}