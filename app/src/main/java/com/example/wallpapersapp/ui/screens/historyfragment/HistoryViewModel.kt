package com.example.wallpapersapp.ui.screens.historyfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.model.dto.SearchEntity
import javax.inject.Inject

class HistoryViewModel @Inject constructor(

    private val searchEntity: SearchEntity,
    private val repository: SearchRepositoryImpl

) : ViewModel() {
    val res = repository.searchItems

}