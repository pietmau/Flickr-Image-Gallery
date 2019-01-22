package com.pppp.uscases.main.events

import com.pppp.entites.FlickrImage

sealed class Effect {
    object GetAllImages : Effect()
    data class ShowDetail(val detail: Detail) : Effect()
    data class GotImages(val images: List<FlickrImage>) : Effect()
}

interface Detail {
    val image: FlickrImage
    val position: Int
    val id: String
    val imageUrl: String
}