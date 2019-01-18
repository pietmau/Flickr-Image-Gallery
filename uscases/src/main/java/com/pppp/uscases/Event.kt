package com.pppp.uscases

import com.pppp.entites.Entry

sealed class Event {
    data class LoadComplete(val results: List<Entry>) : Event()
    data class Error(val throwable: Throwable) : Event()
}