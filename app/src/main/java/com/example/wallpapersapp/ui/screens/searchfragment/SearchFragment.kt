package com.example.wallpapersapp.ui.screens.searchfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.model.Results
import com.example.wallpapersapp.R
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentTestBinding
import com.example.wallpapersapp.ui.screens.searchfragment.adapter.ImagesAdapter
import com.example.wallpapersapp.util.ext.SearchViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: SearchFragmentViewModel by viewModels {
        factory.create("some text")
    }
    private var keyDel = false

    private var searchJob: Job? = null
    private val adapter by lazy {
        ImagesAdapter(requireContext()) { result -> imageDetails(result) }
    }

    @Inject
    lateinit var factory: SearchViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("ClickableViewAccessibility")
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.queryTextInput.textChanges()
            .debounce(500)
            .onEach { search(it.toString()) }
            .launchIn(lifecycleScope)

        binding.recycler.adapter = adapter

        binding.outlinedTextField.setEndIconOnClickListener {
            changeView(requireContext())
        }

        return binding.root
    }

    private fun imageDetails(result: Results) {
        val action = SearchFragmentDirections.actionToImageDetails(result)
        findNavController().navigate(action)
    }


    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeView(context: Context) {
        when ((binding.recycler.layoutManager as GridLayoutManager).spanCount) {
            2 -> {
                binding.recycler.layoutManager = setLayoutManager(3)
                binding.outlinedTextField.endIconDrawable =
                    context.getDrawable(R.drawable.ic_three_rows)
            }
            3 -> {
                binding.recycler.layoutManager = setLayoutManager(2)
                binding.outlinedTextField.endIconDrawable =
                    context.getDrawable(R.drawable.ic_two_rows)
            }
        }
    }

    private fun setLayoutManager(spanCount: Int): GridLayoutManager {
        return GridLayoutManager(requireContext(), spanCount)
    }

    @ExperimentalCoroutinesApi
    fun EditText.textChanges(): Flow<CharSequence?> {
        return callbackFlow {
            val listener = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count < before) return else trySend(s)
                }
            }
            addTextChangedListener(listener)
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }
}