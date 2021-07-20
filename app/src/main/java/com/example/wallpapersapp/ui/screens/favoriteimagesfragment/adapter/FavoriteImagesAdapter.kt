package com.example.wallpapersapp.ui.screens.favoriteimagesfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.data.db.ImageAndUser
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.FavImagesItemBinding

class FavoriteImagesAdapter :
    ListAdapter<ImageAndUser, FavoriteImagesAdapter.ImagesViewHolder>(ImagesDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fav_images_item, parent, false)
        return ImagesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = FavImagesItemBinding.bind(itemView)

        fun bind(images: ImageAndUser?) = with(itemView) {

            binding.favImageView.load(images?.imagesDb?.urls?.regular) {
//            placeholder(ColorDrawable(Color.TRANSPARENT))
                crossfade(1000)
                crossfade(true)
                error(R.drawable.ic_broken_image)
            }

//            binding.photoImageView.setOnClickListener {
//                imageDetails(images!!)
//                Log.d("asd","CLICKED!")
//            }
        }

    }
}


private object ImagesDiffCallback : DiffUtil.ItemCallback<ImageAndUser>() {
    override fun areItemsTheSame(oldItem: ImageAndUser, newItem: ImageAndUser): Boolean =
        oldItem.imagesDb.imageId == newItem.imagesDb.imageId

    override fun areContentsTheSame(oldItem: ImageAndUser, newItem: ImageAndUser): Boolean =
        oldItem.imagesDb.urls.regular == newItem.imagesDb.urls.regular
}