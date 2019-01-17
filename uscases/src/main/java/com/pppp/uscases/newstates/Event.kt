package com.pppp.uscases.newstates

sealed class Event {
    data class LoadComplete(val results: List<Any>) : Event()
    data class Error(val throwable: Throwable) : Event()
}