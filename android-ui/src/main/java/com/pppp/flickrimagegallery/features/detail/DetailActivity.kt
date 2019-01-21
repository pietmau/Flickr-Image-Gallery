package com.pppp.flickrimagegallery.features.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.flickrimagegallery.R

internal class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
    }

    companion object {
        const val ID = "id"
        // to be used for shared element transition
        const val POSITION = "position"
    }
}