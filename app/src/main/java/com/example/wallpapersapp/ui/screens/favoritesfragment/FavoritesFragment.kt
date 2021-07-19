package com.example.wallpapersapp.ui.screens.favoritesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.FragmentFavoritesBinding
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.FavoriteImagesFragment
import com.example.wallpapersapp.ui.screens.favoritesfragment.adapters.CollectionAdapter
import com.example.wallpapersapp.ui.screens.searchrequestsfragment.SearchRequestsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)

        binding.viewPager2.adapter = ScreenSlidePagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when(position){
                0-> tab.setIcon(R.drawable.ic_image)
                1->tab.setIcon(R.drawable.ic_bookmark)
            }
        }.attach()

        return binding.root
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return FavoriteImagesFragment()
                1 -> return SearchRequestsFragment()
            }
            return FavoriteImagesFragment()
        }

    }
}

