package com.example.wallpapersapp.ui.screens.imagedetailsfragment

import android.annotation.SuppressLint
import android.app.Application
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.opengl.GLSurfaceView
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.data.db.getDatabase
import com.example.data.model.Results
import com.example.data.repository.ImagesRepositoryImpl
import com.example.wallpapersapp.R
import com.example.wallpapersapp.databinding.ImageDetailsFragmentBinding
import com.example.wallpapersapp.util.ext.ImageDetailsViewModelFactory
import com.google.android.play.core.internal.a
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class ImageDetailsFragment : Fragment() {

    private lateinit var binding: ImageDetailsFragmentBinding

    private val args: ImageDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: ImageDetailsViewModel
    private lateinit var viewModelFactory: ImageDetailsViewModelFactory

    @SuppressLint("WebViewApiAvailability")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ImageDetailsFragmentBinding.inflate(inflater)

        viewModelFactory = ImageDetailsViewModelFactory(args.currentImage, activity?.application!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageDetailsViewModel::class.java)

        binding.viewModel = viewModel
        setToolbar()

        binding.photoImageView.apply {
            load(viewModel.currentImage.urls.regular)
            setOnClickListener { binding.motionlayout.transitionToStart() }
        }

        binding.about.circleImageView.load(viewModel.currentImage.user.profileImage.medium)

        checkSocialMedia()

        binding.myToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.bottomBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.share_bottom_menu -> shareImage()
                R.id.tune_bottom_menu -> binding.motionlayout.transitionToState(R.id.show_features)
                R.id.info_bottom_menu -> binding.motionlayout.transitionToState(R.id.show_about)
            }
            false
        }

        binding.about.portfolioButton.setOnClickListener {
            val portfolioUrl = viewModel.currentImage.user.portfolioUrl
            if (portfolioUrl.isNullOrEmpty()) Toast.makeText(
                requireContext(),
                "Portfolio is empty",
                Toast.LENGTH_SHORT
            ).show() else goToPortfolio(portfolioUrl)
        }

        binding.desktopWallpaperTextView.setOnClickListener { setWallpaper(true) }
        binding.lockScreenTextView.setOnClickListener { setWallpaper(false) }
        return binding.root
    }

    private fun setWallpaper(chooser: Boolean) {
        val imgBitmap = (binding.photoImageView.drawable as BitmapDrawable).bitmap
        val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(requireContext())
        when (chooser) {
            true -> wallpaperManager.setBitmap(imgBitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            false -> wallpaperManager.setBitmap(imgBitmap, null, true, WallpaperManager.FLAG_LOCK)
        }
        Toast.makeText(requireContext(), "Wallpapers set!", Toast.LENGTH_SHORT).show()
    }

    private fun goToPortfolio(link: String) {
        val action =
            ImageDetailsFragmentDirections.actionImageDetailsFragmentToWebViewFragment(link)
        findNavController().navigate(action)
    }

    private fun setToolbar() {
        binding.myToolbar.title = viewModel.currentImage.tags?.get(0)?.title
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.myToolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun shareImage() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, args.currentImage.urls.regular)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.send_to)))
    }

    private fun checkSocialMedia() {
        if (viewModel.currentImage.user.instagramUsername.isNullOrEmpty()) binding.about.userInstagramTextView.visibility =
            View.GONE
        if (viewModel.currentImage.user.twitterUsername.isNullOrEmpty()) binding.about.userTwitterTextView.visibility =
            View.GONE
    }

}

