package com.ahmed.orangechallenge.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ahmed.orangechallenge.R
import com.bumptech.glide.Glide

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun ImageView.loadImageFromUrl(url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(this.context).load(url).placeholder(circularProgressDrawable)
        .error(R.mipmap.ic_launcher).into(this)
}

fun ImageView.loadImageFromUri(context: Context, url: String?) {
    Glide.with(context).load(url).into(this)
}

