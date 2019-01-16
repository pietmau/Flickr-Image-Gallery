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
import dagger.Module
import dagger.Provides

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
    fun provideLifecycleOwner(mainActivity: MainActivity) = mainActivity as LifecycleOwner

    @Provides
    fun provideFactory(store: ViewStateStore): ViewModelProvider.Factory = MainViewModelFactory(store)

    class MainViewModelFactory(val store: ViewStateStore) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T = throw Exception("")//LiveDataMainViewModel(store) as T
    }
}