package com.pppp.uscases

import com.pppp.entites.FlickrImage

sealed class Event {
    data class LoadComplete(val results: List<FlickrImage>) : Event()
    data class Error(val throwable: Throwable) : Event()
}