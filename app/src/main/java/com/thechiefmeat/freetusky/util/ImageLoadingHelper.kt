@file:JvmName("ImageLoadingHelper")

package com.thechiefmeat.freetusky.util

import android.widget.ImageView
import androidx.annotation.Px
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.thechiefmeat.freetusky.R


private val fitCenterTransformation = FitCenter()

fun loadAvatar(url: String?, imageView: ImageView, @Px radius: Int, animate: Boolean) {

    if(url.isNullOrBlank()) {
        Glide.with(imageView)
                .load(R.drawable.avatar_default)
                .into(imageView)
    } else {
        if (animate) {
            Glide.with(imageView)
                    .load(url)
                    .transform(
                            fitCenterTransformation,
                            RoundedCorners(radius)
                    )
                    .placeholder(R.drawable.avatar_default)
                    .into(imageView)

        } else {
            Glide.with(imageView)
                    .asBitmap()
                    .load(url)
                    .transform(
                            fitCenterTransformation,
                            RoundedCorners(radius)
                    )
                    .placeholder(R.drawable.avatar_default)
                    .into(imageView)
        }

    }
}