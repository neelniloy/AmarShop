package com.braineer.amarshopbyseo.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.braineer.amarshopbyseo.R
import com.bumptech.glide.Glide

@BindingAdapter("app:setDate")
fun setFormattedDate(tv: TextView, date: Long) {
    //tv.text = getFormattedDateTime(date, "dd/MM/yyyy")
}

@BindingAdapter("app:setTime")
fun setFormattedTime(tv: TextView, date: Long) {
    //tv.text = getFormattedDateTime(date, "hh:mm a")
}

@BindingAdapter("app:setImageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.logo)
            .into(imageView)
    }
}