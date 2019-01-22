package com.pppp.flickrimagegallery.application.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(
        view: ImageView, url: String?, success: Success? = {}, failure: Failure? = {},
        resize: Boolean = true
    )

    fun cancelTask(image: ImageView) {}
}
typealias Success = () -> Unit

typealias Failure = (Exception?) -> Unit
