package com.example.wallpapersapp.ui.screens.testfragment.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transition.Transition
import com.example.data.model.Results
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.GridItemViewBinding

class ImagesAdapter(context: Context) :
    PagingDataAdapter<Results, HomeNewsViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(layoutInflater.inflate(R.layout.grid_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = GridItemViewBinding.bind(itemView)

    fun bind(article: Results?) = with(itemView) {
        binding.marsImage.load(article?.urls?.regular){
//            placeholder(ColorDrawable(Color.TRANSPARENT))
            crossfade(true)
//            crossfade(1000)
            error(R.drawable.ic_broken_image)
        }
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Results>() {

    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id == newItem.id
    }
}