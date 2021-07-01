package com.example.wallpapersapp.ui.screens.testfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.wallpapersapp.databinding.FragmentTestBinding
import com.example.wallpapersapp.ui.screens.testfragment.adapter.ImageGridAdapter

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: TestFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recycler.adapter = ImageGridAdapter()

//        viewModel.refreshPhotos().observe(viewLifecycleOwner, {
//            Log.d("asd", it[0].links.full)
//            binding.recycler.adapter = ImageGridAdapter()
//        })
//        binding.textId.text = "ASDASDASDASDASD"
//        binding.imageId.load(R.drawable.logo)


        Log.d("asd", "ASDASD")
        return binding.root
    }

}