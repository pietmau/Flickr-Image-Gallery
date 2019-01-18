package com.pppp.uscases

import com.pppp.entites.Entry

sealed class Model {
    object Starting : Model()
    object Loading : Model()
    data class Complete(val result: List<Entry>) : Model()
    data class Error(val throwable: Throwable) : Model()
}