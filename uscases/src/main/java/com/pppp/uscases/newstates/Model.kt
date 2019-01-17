package com.pppp.uscases.newstates

sealed class Model {
    object Starting : Model()
    object Loading : Model()
    data class Complete(val result: List<Any>) : Model()
    data class Error(val throwable: Throwable) : Model()
}