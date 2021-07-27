package com.example.wallpapersapp.ui.screens.searchrequestsfragment.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.db.searchdb.SearchDb
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.FavoritesSearchesCardViewBinding

class SearchRequestsAdapter(
    val delete: (
        searchDb: SearchDb
    ) -> Unit,
    val goToSearch: (
        searchQuery: String
    ) -> Unit,
    val timeConverter: (
        time: String
    ) -> String
) :
    ListAdapter<SearchDb, SearchRequestsAdapter.SearchesViewHolder>(SearchesDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorites_searches_card_view, parent, false)
        return SearchesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = FavoritesSearchesCardViewBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(searches: SearchDb?) = with(itemView) {
            binding.resultAmountTextView.text =
                "${searches?.resultAmount.toString()} результатов, ${timeConverter(searches?.searchDate.toString())} "
            binding.searchQueryTextView.text = searches?.searchQuery
            binding.deleteImageView.setOnClickListener {
                if (searches != null) {
                    delete(searches)
                }
            }
            binding.root.setOnClickListener {
                goToSearch(searches?.searchQuery.toString())
            }
        }

    }
}


private object SearchesDiffCallback : DiffUtil.ItemCallback<SearchDb>() {
    override fun areItemsTheSame(oldItem: SearchDb, newItem: SearchDb): Boolean =
        oldItem.searchQuery == newItem.searchQuery

    override fun areContentsTheSame(oldItem: SearchDb, newItem: SearchDb): Boolean =
        oldItem.isFavorite == newItem.isFavorite
}