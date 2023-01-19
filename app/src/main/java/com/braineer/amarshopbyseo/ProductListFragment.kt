package com.braineer.amarshopbyseo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
        imageList.add(SlideModel("https://us.123rf.com/450wm/deagreez/deagreez2002/deagreez200203814/deagreez200203814.jpg"))
        imageList.add(SlideModel("https://www.techprevue.com/wp-content/uploads/2016/09/fashion-online-shopping-sites.jpg"))
        imageList.add(SlideModel("https://t3.ftcdn.net/jpg/03/15/59/88/360_F_315598877_T02bPZdJUfA5khQLnxmq1BWB3ZRuHL6a.jpg"))
        imageList.add(SlideModel("https://images.ctfassets.net/rxqefefl3t5b/6I2vL9f0IVsDQ8qFgdrxH7/7660c4bab3116a4a04025d5c4802efa5/Virgin-Red-online-shopping-offers.jpg" ))
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)

        val adapter = ProductAdapter {binding,product,position->

            binding.cardProduct.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_productDetailsFragment,args = bundleOf("product_id" to product.id))
            }

        }

        val glm = GridLayoutManager(requireActivity(), 2)
        binding.productRV.layoutManager = glm
        binding.productRV.adapter = adapter

        //Fetch
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