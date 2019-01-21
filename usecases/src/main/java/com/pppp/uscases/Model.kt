package com.pppp.uscases

import com.pppp.entites.FlickrImage

sealed class Model {
    object Starting : Model()
    object Loading : Model()
    data class Complete(val result: List<FlickrImage>) : Model()
    data class Error(val throwable: Throwable) : Model()

    data class NavigateToDetail(val detail: Detail, val previousState: Model) : Model() {
        data class Detail(val item: FlickrImage, val position: Int)
    }
}