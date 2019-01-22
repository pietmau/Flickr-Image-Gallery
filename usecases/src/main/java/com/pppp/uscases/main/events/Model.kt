package com.pppp.uscases.main.events

import com.pppp.entites.FlickrImage

sealed class Model {
    object Starting : Model()
    object Loading : Model()
    data class Complete(val result: List<FlickrImage>) : Model()
    data class Error(val throwable: Throwable) : Model()

    data class NavigateToDetail(
        val detail: Detail,
        // is needed to navigate back, not nice
        val previousState: Model
    ) : Model()

    data class Warning(
        val messageCode: Int? = null,
        // is needed to avoid showing the same message again after config changes, not nice
        val previousState: Model
    ) : Model()
}