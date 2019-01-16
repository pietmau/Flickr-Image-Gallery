package com.pppp.flickrimagegallery.features.main.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.pppp.flickrimagegallery.features.main.viewmodel.MainViewModel

import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        mainViewModel.observe {

        }
        Handler().postDelayed({
            //mainViewModel.accept()
        }, 5 * 1000)
    }
}
