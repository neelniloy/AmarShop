package com.braineer.amarshopbyseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.braineer.amarshopbyseo.databinding.FragmentMainBinding
import com.braineer.amarshopbyseo.prefdata.LoginPreference
import com.braineer.amarshopbyseo.viewmodels.LoginViewModel
import nl.joery.animatedbottombar.AnimatedBottomBar


class MainFragment : Fragment() {

    private lateinit var binding:FragmentMainBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var preference: LoginPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMainBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        preference = LoginPreference(requireContext())

        preference.isLoggedInFlow
            .asLiveData()
            .observe(viewLifecycleOwner){
                if (!it) {
                    findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                }
            }

        preference.userIdFlow.asLiveData()
            .observe(viewLifecycleOwner) {


            }


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

                if (position == 0) {
                    binding.viewPager.currentItem = 0
                    binding.bottomNav.selectTabAt(0)
                }
                else if (position == 1) {
                    binding.viewPager.currentItem = 1
                    binding.bottomNav.selectTabAt(1)
                }
                else if (position == 2) {
                    binding.viewPager.currentItem = 2
                    binding.bottomNav.selectTabAt(2)
                }

                super.onPageSelected(position)
            }
        })


        binding.viewPager.adapter = DashboardAdapter(requireActivity())

        binding.viewPager.isUserInputEnabled = false

        binding.bottomNav.setupWithViewPager2(binding.viewPager)

        //set badge
        binding.bottomNav.setBadgeAtTabIndex(1, AnimatedBottomBar.Badge("7"))
        //binding.bottomNav.clearBadgeAtTabIndex(1)

        binding.bottomNav.setOnTabInterceptListener(object : AnimatedBottomBar.OnTabInterceptListener {
            override fun onTabIntercepted(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ): Boolean {
                when(newTab.id){
                    R.id.tab_home -> {
                        binding.viewPager.currentItem = 0
                    }
                    R.id.tab_cart -> {
                        binding.viewPager.currentItem = 1
                    }
                    R.id.tab_account -> {
                        binding.viewPager.currentItem = 2
                    }else-> return false
                }
                return true
            }
        })

        return binding.root
    }

    private inner class DashboardAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProductListFragment()
                1 -> CartFragment()
                2 -> AccountFragment()
                else -> {
                    ProductListFragment()
                }
            }
        }
    }
}