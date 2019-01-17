package com.pppp.flickrimagegallery.features.main.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import com.pppp.flickrimagegallery.features.main.viewmodel.LiveDataMainViewModel
import com.pppp.flickrimagegallery.features.main.viewmodel.MainViewModel
import com.pppp.flickrimagegallery.features.main.viewmodel.ViewStateStore
import com.pppp.uscases.UseCasesModule
import com.pppp.uscases.ViewState
import com.pppp.uscases.newstates.Effect
import com.pppp.uscases.newstates.Event
import com.pppp.uscases.newstates.Model
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module(includes = [UseCasesModule::class])
class MainModule {

    @Provides
    fun provideModel(
        mainActivity: MainActivity,
        factory: ViewModelProvider.Factory
    ): MainViewModel = ViewModelProviders.of(mainActivity, factory).get(LiveDataMainViewModel::class.java)

    @Provides
    fun provideStore(): ViewStateStore = ViewStateStore(ViewState.Loading)

    @Provides
    fun provideController(
        mainActivity: MainActivity,
        factory: AndroidViewModelFactory
    ): MobiusLoop.Controller<Model, Event> =
        ViewModelProviders.of(mainActivity, factory).get(AndroidViewModel::class.java).controller

    @Provides
    fun provideLifecycleOwner(mainActivity: MainActivity) = mainActivity as LifecycleOwner

    @Provides
    fun provideFactory(store: ViewStateStore): ViewModelProvider.Factory = MainViewModelFactory(store)

    class MainViewModelFactory(val store: ViewStateStore) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            throw Exception("")//LiveDataMainViewModel(store) as T
    }

    data class AndroidViewModel(val controller: MobiusLoop.Controller<Model, Event>) : ViewModel()

    class AndroidViewModelFactory @Inject constructor(val loopFactory: MobiusLoop.Builder<Model, Event, Effect>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            AndroidViewModel(MobiusAndroid.controller(loopFactory, Model.Starting)) as T
    }
}