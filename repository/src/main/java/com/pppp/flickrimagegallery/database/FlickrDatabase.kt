package com.pppp.flickrimagegallery.database

import com.pppp.entites.FlickrImage

//TODO make internal
interface FlickrDatabase<in T:FlickrImage> {

    fun insert(images: List<T>)
}
