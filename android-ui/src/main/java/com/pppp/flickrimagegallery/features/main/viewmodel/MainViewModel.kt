package com.pppp.flickrimagegallery.features.main.viewmodel

import androidx.core.util.Consumer
import androidx.lifecycle.LifecycleOwner
import com.pppp.uscases.MainIntent
import com.pppp.uscases.ViewState

class LiveDataMainViewModel(
    private val lifecycleOwner: LifecycleOwner,
    private val store: ViewStateStore<in ViewState> = ViewStateStore(ViewState.Loading)
) : MainViewModel {

    override fun observe(observer: (T: Any) -> Unit) {
        store.observe(lifecycleOwner, observer)
    }

    override fun accept(intent: MainIntent) {
        store.dispatchState(ViewState.Content(emptyList()))
    }
}

interface MainViewModel : Consumer<MainIntent> {
    fun observe(observer: (Any) -> Unit)
    override fun accept(intent: MainIntent)
}
