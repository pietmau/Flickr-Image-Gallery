package com.pppp.flickrimagegallery.features.main.viewmodel

import androidx.core.util.Consumer
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.pppp.uscases.MainIntent
import com.pppp.uscases.ViewState
import com.pppp.uscases.usecases.UseCases

class LiveDataMainViewModel(
    private val store: ViewStateStore = ViewStateStore(ViewState.Loading),
    private val usecases: UseCases
) : ViewModel(), MainViewModel {

    override fun observe(lifecycleOwner: LifecycleOwner, observer: (ViewState) -> Unit) {
        store.observe(lifecycleOwner, observer)
    }

    override fun accept(intent: MainIntent) {}
}

interface MainViewModel : Consumer<MainIntent> {
    fun observe(lifecycleOwner: LifecycleOwner, observer: (ViewState) -> Unit)
    override fun accept(intent: MainIntent)
}
