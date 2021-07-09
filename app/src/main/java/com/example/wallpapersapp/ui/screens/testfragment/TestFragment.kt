package com.example.wallpapersapp.ui.screens.testfragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentTestBinding
import com.example.wallpapersapp.ui.screens.testfragment.adapter.ImagesAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: TestFragmentViewModel by viewModels {
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

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTestBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.queryEditText.textChanges()
            .debounce(300)
            .onEach { search(it.toString()) }
            .launchIn(lifecycleScope)

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
                    trySend(s)
                }
            }
            addTextChangedListener(listener)
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }
}