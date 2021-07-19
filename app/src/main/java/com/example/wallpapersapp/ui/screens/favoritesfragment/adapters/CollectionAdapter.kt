package com.example.wallpapersapp.ui.screens.favoritesfragment.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.FavoriteImagesFragment

class CollectionAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = FavoriteImagesFragment()

}