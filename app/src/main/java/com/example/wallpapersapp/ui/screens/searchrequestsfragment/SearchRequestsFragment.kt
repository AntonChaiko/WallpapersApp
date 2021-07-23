package com.example.wallpapersapp.ui.screens.searchrequestsfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wallpapersapp.R
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentSearchRequestsBinding
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.FavoriteImagesViewModel
import com.example.wallpapersapp.util.ext.FavoriteImagesViewModelFactory
import com.example.wallpapersapp.util.ext.SearchRequestViewModelFactory
import javax.inject.Inject

class SearchRequestsFragment : Fragment() {

    private lateinit var binding: FragmentSearchRequestsBinding

    @Inject
    lateinit var factory: SearchRequestViewModelFactory.Factory


    val viewModel: SearchRequestsViewModel by viewModels {
        factory.create("text")
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchRequestsBinding.inflate(inflater)


        viewModel.res.observe(viewLifecycleOwner, {
            it.forEach {
                binding.testTextView.append("${it.searchQuery} \n")
            }
        })

        return binding.root
    }


}