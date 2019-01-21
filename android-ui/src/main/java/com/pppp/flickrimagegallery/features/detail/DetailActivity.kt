package com.pppp.flickrimagegallery.features.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.flickrimagegallery.R
import com.pppp.flickrimagegallery.application.ImageLoader
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var imageLoader: ImageLoader
    //TODO use repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        AndroidInjection.inject(this)
        val url = intent.getStringExtra(IMAGE_URL)
        imageLoader.loadImage(view = detail_image, url = url, resize = false)
    }

    companion object {
        const val ID = "id"
        const val IMAGE_URL = "image_url"
        // to be used for shared element transition
        const val POSITION = "position"
    }
}