package com.pppp.flickrimagegallery.mainfeature.viewmodel

import androidx.lifecycle.ViewModel
import com.pppp.uscases.ViewState

class MainViewModel : ViewModel() {

    val store = StateStore(ViewState.Loading)
}