package com.pppp.uscases

import com.pppp.entites.FlickrImage

sealed class Event {
    data class LoadComplete(val images: List<FlickrImage>) : Event()
    data class Error(val throwable: Throwable) : Event()
    data class DetailSelected(val detail: Detail) : Event()
    data class ShowDetail(val detail: Detail) : Event()
}
