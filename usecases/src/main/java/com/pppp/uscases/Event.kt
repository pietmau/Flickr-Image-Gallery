package com.pppp.uscases

import com.pppp.entites.FlickrImage

sealed class Event {
    data class LoadComplete(val images: List<FlickrImage>) : Event()
    data class Error(val throwable: Throwable) : Event()
    data class DetailClicked(val item: FlickrImage, val position: Int) : Event()
}