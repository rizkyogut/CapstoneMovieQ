package com.q.capstonemovieq.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.q.capstonemovieq.core.constant.Constants

fun ImageView.setImage(url: String) {
    Glide.with(this.context)
        .load(Constants.POSTER_ORIGINAL_IMAGE_PATH + url)
        .into(this)
}