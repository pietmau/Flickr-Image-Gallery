package com.pppp.flickrimagegallery.features.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.flickrimagegallery.features.main.view.MainActivity
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
class MainModule {

    @Provides
    fun provideController(
        mainActivity: MainActivity,
        factory: AndroidViewModelFactory
    ): MobiusLoop.Controller<Model, Event> =
        ViewModelProviders.of(mainActivity, factory).get(AndroidViewModel::class.java).controller

    @Inject
    @Provides
    fun provideClickBllocker(blocker: ClickBlockerImpl): ClickBlocker = blocker

    @Inject
    @Provides
    fun provideLoader(): ImageLoader = PicassoImageLoader

    data class AndroidViewModel(val controller: MobiusLoop.Controller<Model, Event>) : ViewModel()

    class AndroidViewModelFactory @Inject constructor(val loopFactory: MobiusLoop.Builder<Model, Event, Effect>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            AndroidViewModel(MobiusAndroid.controller(loopFactory, Model.Starting)) as T
    }
}