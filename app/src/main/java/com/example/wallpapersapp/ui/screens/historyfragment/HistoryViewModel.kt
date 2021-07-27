package com.example.wallpapersapp.ui.screens.historyfragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.searchdb.SearchDb
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.model.dto.SearchEntity
import kotlinx.coroutines.launch
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HistoryViewModel @Inject constructor(

    private val searchEntity: SearchEntity,
    private val repository: SearchRepositoryImpl

) : ViewModel() {
    val res = repository.searchItems

    fun updateSearchDatabase(searchDb: SearchDb) {
        viewModelScope.launch {
            searchDb.isFavorite = !searchDb.isFavorite
            repository.updateSearch(searchDb)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun timeConverter(time: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val timeAgo = sdf.parse(time).time
        val p = PrettyTime(Locale("ru"))
        return p.format(Date(timeAgo))
    }
}