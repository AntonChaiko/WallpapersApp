package com.example.wallpapersapp.ui.screens.searchrequestsfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wallpapersapp.R
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentSearchRequestsBinding
import com.example.wallpapersapp.ui.screens.searchrequestsfragment.adapter.SearchRequestsAdapter
import com.example.wallpapersapp.util.ext.SearchRequestViewModelFactory
import javax.inject.Inject

class SearchRequestsFragment : Fragment() {

    private lateinit var binding: FragmentSearchRequestsBinding

    @Inject
    lateinit var factory: SearchRequestViewModelFactory.Factory


    val viewModel: SearchRequestsViewModel by viewModels {
        factory.create("")
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
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        val adapter = SearchRequestsAdapter(
            delete = { viewModel.deleteSearch(it) },
            goToSearch = { goToSearch(it) },
            timeConverter = { viewModel.timeConverter(it) }
        )

        viewModel.res.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        binding.favoritesSearchRecyclerView.adapter = adapter
    }

    private fun goToSearch(searchQuery: String) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString("SEARCH_FIELD", searchQuery)
        }?.apply()
        findNavController().navigate(R.id.action_favoritesFragment_to_testFragment)
    }
}