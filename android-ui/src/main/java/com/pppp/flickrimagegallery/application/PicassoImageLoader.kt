package com.pppp.flickrimagegallery.application

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object PicassoImageLoader : ImageLoader {
    private const val DEFAULT_SIZE = 400

    override fun loadImage(
        view: ImageView,
        url: String?,
        success: Success?,
        failure: Failure?,
        resize: Boolean
    ) {
        url ?: return
        Picasso.get()
            .load(url)
            .resize(resize, DEFAULT_SIZE)
            .into(view, object : Callback {
                override fun onSuccess() {
                    success?.invoke()
                }

                override fun onError(exception: Exception?) {
                    failure?.invoke(exception)
                }
            })
    }

    override fun cancelTask(image: ImageView) {
        Picasso.get().cancelRequest(image)
    }

    fun RequestCreator.resize(shouldResize: Boolean, size: Int): RequestCreator =
        if (shouldResize) {
            this.resize(size, size).centerCrop()
        } else {
            this
        }

}
