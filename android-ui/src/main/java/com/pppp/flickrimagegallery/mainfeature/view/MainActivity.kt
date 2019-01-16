package com.pppp.flickrimagegallery.mainfeature.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.pppp.flickrimagegallery.mainfeature.viewmodel.LiveDataMainViewModel
import com.pppp.uscases.ViewState

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(LiveDataMainViewModel::class.java)
        viewModel.store.observe(this) {


        }

        Handler().postDelayed({
            viewModel.store.liveData.value = ViewState.Content(emptyList())
        }, 5 * 1000)
    }
}
