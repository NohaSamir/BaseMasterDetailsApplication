package com.example.basemasterdetailsapplication.ui

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.basemasterdetailsapplication.R
import com.example.basemasterdetailsapplication.domain.DataStatus

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {

    url?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()

        Glide.with(imageView.context)
            .load(imgUri)
            .apply(RequestOptions().placeholder(R.drawable.loading_img).error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("bindDataStatus")
fun bindDataStatus(imageView: ImageView, status: DataStatus?) {
    when (status) {
        DataStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }

        DataStatus.ERROR -> {
            /*imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
*/
            imageView.visibility = View.GONE
        }

        DataStatus.SUCCESS -> {
            imageView.visibility = View.GONE
        }
    }
}

