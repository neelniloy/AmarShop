package com.braineer.amarshopbyseo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.braineer.amarshopbyseo.adapters.ProductAdapter
import com.braineer.amarshopbyseo.databinding.FragmentProductListBinding
import com.braineer.amarshopbyseo.model.ProductModel
import com.braineer.amarshopbyseo.viewmodels.ProductViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private val productViewModel: ProductViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductListBinding.inflate(inflater, container, false)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://www.layaji.in/uploads/sliders/Grocery-home-banner_(1).jpg"))
        imageList.add(SlideModel("https://www.callkirana.in//assets/img/slider/b864eb3751a71ecdfd5682a5048bcf0c.png"))
        imageList.add(SlideModel("https://cdn.shopify.com/s/files/1/0271/7070/1390/articles/advantages-to-buy-from-online-grocery-store-250453_1000x.jpg"))
        imageList.add(SlideModel("https://www.kindpng.com/picc/m/241-2413641_all-type-of-grocery-items-available-grocery-items.png" ))
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)

        val adapter = ProductAdapter ()

        val glm = GridLayoutManager(requireActivity(), 2)
        binding.productRV.layoutManager = glm
        binding.productRV.adapter = adapter

        productViewModel.getAllProduct()

        productViewModel.productListLD.observe(viewLifecycleOwner) {productList ->

            if (productList.isEmpty()) {
                binding.mProgressBar.visibility = View.VISIBLE
            } else {
                binding.mProgressBar.visibility = View.GONE
                adapter.submitList(productList)
            }

            binding.searchBar.addTextChangedListener (object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    if (s!!.isEmpty()) {
                        binding.cardSlide.visibility=View.VISIBLE
                        adapter.submitList(productList)
                    }else{
                        binding.cardSlide.visibility=View.GONE
                        val tempList = mutableListOf<ProductModel>()
                        for (product in productList) {
                            if(product.title.contains(s) || product.title.lowercase().contains(s)){
                                tempList.add(product)
                            }
                        }
                        adapter.submitList(tempList)
                    }
                }
            })



        }


        return binding.root
    }

}