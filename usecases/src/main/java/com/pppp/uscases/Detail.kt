package com.pppp.uscases

import com.pppp.entites.FlickrImage

interface Detail {
    val image: FlickrImage
    val position: Int
    val imageLoaded: Boolean
    val id: String
    val imageUrl: String
}