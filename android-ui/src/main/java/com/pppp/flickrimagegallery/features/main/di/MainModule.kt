package com.pppp.flickrimagegallery.features.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.flickrimagegallery.features.main.view.controller.MobiusController
import com.pppp.flickrimagegallery.features.main.view.customview.ImageLoader
import com.pppp.flickrimagegallery.features.main.view.customview.PicassoImageLoader
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
object MainModule {

    @JvmStatic
    @Provides
    fun provideController(
        mainActivity: MainActivity,
        factory: AndroidViewModelFactory
    ): Controller<Model, Event> =
        ViewModelProviders.of(mainActivity, factory).get(AndroidViewModel::class.java).controller

    @JvmStatic
    @Provides
    fun provideLoader(): ImageLoader = PicassoImageLoader

    data class AndroidViewModel(val controller: Controller<Model, Event>) : ViewModel()

    class AndroidViewModelFactory
    @Inject constructor(private val loopFactory: MobiusLoop.Builder<Model, Event, Effect>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val mobiusController = MobiusAndroid.controller(loopFactory, Model.Starting)
            val controller = MobiusController<Model, Event>(mobiusController)
            return AndroidViewModel(controller) as T
        }
    }
}
