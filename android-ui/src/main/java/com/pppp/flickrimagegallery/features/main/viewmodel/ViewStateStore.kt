package com.pppp.flickrimagegallery.features.main.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pppp.uscases.ViewState

class ViewStateStore(initialState: ViewState) {

    private val liveData = MutableLiveData<ViewState>().apply {
        value = initialState
    }

    fun observe(owner: LifecycleOwner, observer: (ViewState) -> Unit) =
        liveData.observe(owner, Observer { observer(it) })

    fun dispatchState(state: ViewState) {
        liveData.postValue(state)
    }

    fun state() = liveData.value
}