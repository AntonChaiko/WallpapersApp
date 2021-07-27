package com.example.wallpapersapp.ui.screens.historyfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.model.dto.SearchEntity
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentHistoryBinding
import com.example.wallpapersapp.ui.screens.historyfragment.adapter.HistorySearchAdapter
import com.example.wallpapersapp.util.ext.HistoryViewModelFactory
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    @Inject
    lateinit var factory: HistoryViewModelFactory.Factory

    private val viewModel: HistoryViewModel by viewModels {
        factory.create(SearchEntity("", 4, "asd", false))
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

        binding = FragmentHistoryBinding.inflate(inflater)
        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        val adapter = HistorySearchAdapter(
            updateQuery = { viewModel.updateSearchDatabase(it) },
            timeConverter = { viewModel.timeConverter(it) }
        )

        viewModel.res.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        binding.historyRecyclerView.adapter = adapter
    }


}