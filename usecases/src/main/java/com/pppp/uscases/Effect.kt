package com.pppp.uscases

import com.pppp.entites.FlickrImage

sealed class Effect {
    object GetAllImages : Effect()
    data class ShowDetail(val detail: Detail) : Effect() {
        val imageLoaded = detail.imageLoaded
    }

    data class GotImages(val images: List<FlickrImage>) : Effect()
}