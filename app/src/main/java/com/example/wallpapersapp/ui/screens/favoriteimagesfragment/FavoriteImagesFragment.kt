package com.example.wallpapersapp.ui.screens.favoriteimagesfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import asResultModel
import com.example.data.model.Results
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentFavoriteImagesBinding
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.adapter.FavoriteImagesAdapter
import com.example.wallpapersapp.ui.screens.favoritesfragment.FavoritesFragmentDirections
import com.example.wallpapersapp.util.ext.FavoriteImagesViewModelFactory
import javax.inject.Inject

class FavoriteImagesFragment : Fragment() {


    private lateinit var binding: FragmentFavoriteImagesBinding

    @Inject
    lateinit var factory: FavoriteImagesViewModelFactory.Factory

    val viewModel: FavoriteImagesViewModel by viewModels {
        factory.create("image")
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteImagesBinding.inflate(inflater)

        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        val adapter = FavoriteImagesAdapter(
            deleteUser = { viewModel.deleteImg(it) },
            goToDetails = { imageDetails(it.asResultModel()) }
        )
        viewModel.getImagesLiveData().observe(viewLifecycleOwner, {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        binding.favoritesRecyclerView.adapter = adapter
    }

    private fun imageDetails(result: Results) {
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToImageDetailsFragment(result)
        findNavController().navigate(action)
    }


}