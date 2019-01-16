package com.pppp.flickrimagegallery.mainfeature.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.pppp.uscases.ViewState

class LiveDataMainViewModel<T : ViewState>(
    private val lifecycleObserver: LifecycleObserver,
    private val store: ViewStateStore<in T> = ViewStateStore<ViewState>(ViewState.Loading)
) :
    ViewModel(), MainViewModel {


}

interface MainViewModel
