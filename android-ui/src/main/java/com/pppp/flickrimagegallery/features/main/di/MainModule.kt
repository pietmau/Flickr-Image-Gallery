package com.pppp.flickrimagegallery.features.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import com.pppp.flickrimagegallery.features.main.view.controller.Controller
import com.pppp.flickrimagegallery.features.main.view.controller.MobiusController
import com.pppp.mvicoreapp.main.view.customview.ClickBlocker
import com.pppp.mvicoreapp.main.view.customview.ClickBlockerImpl
import com.pppp.mvicoreapp.main.view.customview.ImageLoader
import com.pppp.mvicoreapp.main.view.customview.PicassoImageLoader
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.Model
import com.pppp.uscases.di.UseCasesModule
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module(includes = [UseCasesModule::class])
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
    fun provideClickBllocker(): ClickBlocker = ClickBlockerImpl()

    @JvmStatic
    @Provides
    fun provideLoader(): ImageLoader = PicassoImageLoader

    data class AndroidViewModel(val controller: Controller<Model, Event>) : ViewModel()

    class AndroidViewModelFactory @Inject constructor(private val loopFactory: MobiusLoop.Builder<Model, Event, Effect>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            AndroidViewModel(MobiusController<Model, Event>(MobiusAndroid.controller(loopFactory, Model.Starting))) as T
    }

}