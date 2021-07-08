package com.example.wallpapersapp.ui.screens.testfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentTestBinding
import com.example.wallpapersapp.ui.screens.testfragment.adapter.ImagesAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: TestFragmentViewModel by viewModels{
        factory.create("some text")
    }

    private var searchJob: Job? = null

    private val adapter by lazy {
        ImagesAdapter(requireContext())
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
        binding.lifecycleOwner = this
        search("cat")
        binding.recycler.adapter = adapter
        return binding.root
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

}