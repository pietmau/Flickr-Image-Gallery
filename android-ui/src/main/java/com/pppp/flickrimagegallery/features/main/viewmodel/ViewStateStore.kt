package com.pppp.flickrimagegallery.features.main.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pppp.uscases.ViewState

class ViewStateStore<T : ViewState>(initialState: T) {

    private val liveData = MutableLiveData<T>().apply {
        value = initialState
    }

    fun observe(owner: LifecycleOwner, observer: (T: Any) -> Unit) =
        liveData.observe(owner, Observer { observer(it) })

    private fun dispatchState(state: T) {
        liveData.value = state
    }

    fun state() = liveData.value
}