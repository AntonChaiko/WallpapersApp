package com.example.wallpapersapp.ui.screens.testfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.ImageEntity
import com.example.wallpapersapp.databinding.GridItemViewBinding

class ImageGridAdapter : ListAdapter<ImageEntity, ImageGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ImageEntity>() {

        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.links.full == newItem.links.full

        }
    }

    class MarsPhotoViewHolder(private val binding: GridItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ImageEntity: ImageEntity) {
            binding.photo = ImageEntity.links
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            GridItemViewBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}