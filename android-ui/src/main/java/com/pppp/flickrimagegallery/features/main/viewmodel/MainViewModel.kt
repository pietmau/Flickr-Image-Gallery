package com.pppp.flickrimagegallery.features.main.viewmodel

import androidx.core.util.Consumer
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.pppp.uscases.MainIntent
import com.pppp.uscases.ViewState

class LiveDataMainViewModel<T : ViewState>(
    private val lifecycleOwner: LifecycleOwner,
    private val store: ViewStateStore<in T> = ViewStateStore(ViewState.Loading)
) :
    ViewModel(), MainViewModel {

    override fun observe(observer: (T: Any) -> Unit) {
        store.observe(lifecycleOwner, observer)
    }

    override fun accept(intent: MainIntent) {
        //store.
    }

}

interface MainViewModel : Consumer<MainIntent> {
    fun observe(observer: (Any) -> Unit)
    override fun accept(intent: MainIntent)
}
