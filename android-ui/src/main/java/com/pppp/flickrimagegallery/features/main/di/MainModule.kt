package com.pppp.flickrimagegallery.features.main.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.flickrimagegallery.features.main.view.MainActivity
import com.pppp.flickrimagegallery.features.main.viewmodel.LiveDataMainViewModel
import com.pppp.flickrimagegallery.features.main.viewmodel.MainViewModel
import com.pppp.flickrimagegallery.features.main.viewmodel.ViewStateStore
import com.pppp.uscases.ViewState
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideModel(
        mainActivity: MainActivity,
        factory: ViewModelProvider.Factory
    ): MainViewModel = ViewModelProviders.of(mainActivity, factory).get(LiveDataMainViewModel::class.java)

    @Provides
    fun provideStore(): ViewStateStore<ViewState> = ViewStateStore(ViewState.Loading)

    @Provides
    fun provideLifecycleOwner(mainActivity: MainActivity) = mainActivity as LifecycleOwner

    @Provides
    fun provideFactory(owner: LifecycleOwner, store: ViewStateStore<ViewState>): ViewModelProvider.Factory =
        MainViewModelFactory(owner, store)

    class MainViewModelFactory(val owner: LifecycleOwner, val store: ViewStateStore<ViewState>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T = LiveDataMainViewModel(owner, store) as T
    }
}