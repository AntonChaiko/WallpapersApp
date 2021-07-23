package com.example.wallpapersapp.ui.screens.historyfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.db.searchdb.SearchDb
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.HistoryCardViewBinding

class SearchRequestsAdapter(
    val updateQuery: (searchDb: SearchDb) -> Unit
) :
    ListAdapter<SearchDb, SearchRequestsAdapter.SearchesViewHolder>(SearchesDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_card_view, parent, false)
        return SearchesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = HistoryCardViewBinding.bind(itemView)

        fun bind(searches: SearchDb?) = with(itemView) {
            if (!searches?.searchQuery.isNullOrEmpty()) {
                binding.searchDateTextView.text = searches?.searchDate
                binding.searchQueryTextView.text = searches?.searchQuery
                binding.resultAmountTextView.text = searches?.resultAmount.toString()
                if (searches?.isFavorite == true) binding.favoritesImageButton.setColorFilter(
                    resources.getColor(R.color.main_color)
                ) else binding.favoritesImageButton.setColorFilter(resources.getColor(R.color.text_color))

                binding.favoritesImageButton.setOnClickListener {
                    searches?.let { it1 -> updateQuery(it1) }
                    notifyDataSetChanged()
                }
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