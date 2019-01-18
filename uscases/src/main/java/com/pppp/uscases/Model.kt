package com.pppp.uscases

import com.pppp.entites.FlickrImage

sealed class Model {
    object Starting : Model()
    object Loading : Model()
    data class Complete(val result: List<FlickrImage>) : Model()
    data class Error(val throwable: Throwable) : Model()
}