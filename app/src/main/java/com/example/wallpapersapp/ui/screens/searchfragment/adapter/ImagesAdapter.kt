package com.example.wallpapersapp.ui.screens.searchfragment.adapter

import android.content.Context
import android.util.Log
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

class ImagesAdapter(
    context: Context,
    val imageDetails: (result: Results) -> Unit
) :
    PagingDataAdapter<Results, ImagesAdapter.ImagesViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(layoutInflater.inflate(R.layout.grid_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = GridItemViewBinding.bind(itemView)

        fun bind(images: Results?) = with(itemView) {

            binding.photoImageView.load(images?.urls?.regular) {
//            placeholder(ColorDrawable(Color.TRANSPARENT))
//            crossfade(1000)
                crossfade(true)
                error(R.drawable.ic_broken_image)
            }

            binding.photoImageView.setOnClickListener {
                imageDetails(images!!)
                Log.d("asd","CLICKED!")
            }
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