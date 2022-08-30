package com.q.capstonemovieq.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.q.capstonemovieq.core.constant.Constants

fun ImageView.setPosterImage(url: String) {
    Glide.with(this.context)
        .load(Constants.POSTER_W500_IMAGE_PATH + url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.setHeroImage(url: String) {
    Glide.with(this.context)
        .load(Constants.POSTER_ORIGINAL_IMAGE_PATH + url)
        .transform(FitCenter())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}