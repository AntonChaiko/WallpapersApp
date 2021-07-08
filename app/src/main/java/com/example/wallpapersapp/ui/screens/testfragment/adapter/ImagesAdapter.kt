package com.example.wallpapersapp.ui.screens.testfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.data.model.Results
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.GridItemViewBinding

class ImagesAdapter(context: Context) :
    PagingDataAdapter<Results, ImagesViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(layoutInflater.inflate(R.layout.grid_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = GridItemViewBinding.bind(itemView)

    fun bind(images: Results?) = with(itemView) {
        binding.marsImage.load(images?.urls?.regular){
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