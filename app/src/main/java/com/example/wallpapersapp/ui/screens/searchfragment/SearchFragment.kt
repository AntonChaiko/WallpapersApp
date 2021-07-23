package com.example.wallpapersapp.ui.screens.searchfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.annotation.CheckResult
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.model.Results
import com.example.wallpapersapp.R
import com.example.wallpapersapp.appComponent
import com.example.wallpapersapp.databinding.FragmentTestBinding
import com.example.wallpapersapp.ui.screens.searchfragment.adapter.ImagesAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.whileSelect
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: SearchFragmentViewModel

    private var searchJob: Job? = null
    private var alreadyExecuted = false
    private val adapter by lazy {
        ImagesAdapter(requireContext()) { result -> imageDetails(result) }
    }

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

        viewModel = ViewModelProviders.of(this, factory).get(SearchFragmentViewModel::class.java)

        binding.viewModel = viewModel


        binding.queryTextInput.textChanges()
            .filterNot { it.isNullOrBlank() }
            .debounce(300)
            .onEach {
                viewModel.text = it.toString()
                search(viewModel.text)
            }
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
        viewModel.addSearchQuery(
            viewModel.createEntity(
                binding.queryTextInput.text.toString(),
                viewModel.total,
                DateTime.now().toString(),
                false
            )
        )
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

            val listener = doOnTextChanged { text, _, before, count ->
                if (count < before) return@doOnTextChanged else trySend(text)
            }
            addTextChangedListener(listener)
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }


}