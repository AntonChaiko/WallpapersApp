package com.example.wallpapersapp.ui.screens.favoriteimagesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.FragmentFavoriteImagesBinding
import com.example.wallpapersapp.databinding.ImageDetailsFragmentBinding
import com.example.wallpapersapp.ui.base.BaseFragment
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.adapter.FavoriteImagesAdapter

class FavoriteImagesFragment :
    BaseFragment<FragmentFavoriteImagesBinding, FavoriteImagesViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

    }

    private fun setAdapter() {
        viewModel.getImagesLiveData().observe(viewLifecycleOwner, {
            val adapter = FavoriteImagesAdapter()
            adapter.submitList(it)
            binding.favoritesRecyclerView.adapter = adapter
        })
    }

    override fun getViewModel(): Class<FavoriteImagesViewModel> =
        FavoriteImagesViewModel::class.java

    override fun getFragmentView(): Int = R.layout.fragment_favorite_images


}