package com.braineer.amarshopbyseo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.braineer.amarshopbyseo.databinding.FragmentProductListBinding


class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductListBinding.inflate(inflater, container, false)



        return binding.root
    }

}