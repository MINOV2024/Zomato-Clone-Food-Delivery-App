package com.sanjeet.mealsearchapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sanjeet.mealsearchapp.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    s?.let {
        val options = RequestOptions.placeholderOf(R.drawable.loading).error(R.drawable.error)
        Glide.with(view).setDefaultRequestOptions(options).load(s ?: "").into(view)
    }
}