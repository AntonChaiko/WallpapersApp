package com.example.wallpapersapp.ui.screens.testfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentTestBinding
import com.example.wallpapersapp.ui.screens.testfragment.adapter.ImageGridAdapter
import com.example.wallpapersapp.util.ext.TestViewModelFactory
import javax.inject.Inject

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding

    private val viewModel: TestFragmentViewModel by viewModels{
        factory.create("some text")
    }

    @Inject
    lateinit var factory: NewsDetailsViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTestBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recycler.adapter = ImageGridAdapter()
        return binding.root
    }

}