package com.braineer.amarshopbyseo.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import getFormattedDateTime

@BindingAdapter("app:setDate")
fun setFormattedDate(tv: TextView, date: Long) {
    tv.text = getFormattedDateTime(date, "dd/MM/yyyy")
}

@BindingAdapter("app:setTime")
fun setFormattedTime(tv: TextView, date: Long) {
    tv.text = getFormattedDateTime(date, "hh:mm a")
}