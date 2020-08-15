package com.example.basemasterdetailsapplication.ui

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.basemasterdetailsapplication.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {

    url?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()

        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_img)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("bindDataStatus")
fun bindDataStatus(imageView: ImageView, loading: Boolean) {
    if (loading) {
        imageView.visibility = View.VISIBLE
        imageView.setImageResource(R.drawable.loading_animation)
    } else {
        imageView.visibility = View.GONE
    }
}

