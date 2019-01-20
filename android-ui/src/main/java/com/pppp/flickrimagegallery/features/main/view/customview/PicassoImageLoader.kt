package com.pppp.flickrimagegallery.features.main.view.customview

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object PicassoImageLoader : ImageLoader {
    private const val DEFAULT_SIZE = 400

    override fun loadImage(view: ImageView, url: String?, success: Success?, failure: Failure?) {
        url ?: return
        Picasso.get()
            .load(url)
            .resize(DEFAULT_SIZE, DEFAULT_SIZE)
            .centerCrop()
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
}
