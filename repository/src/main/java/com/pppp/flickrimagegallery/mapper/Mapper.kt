package com.pppp.flickrimagegallery.mapper

import com.pppp.entites.FlickrImage

internal interface Mapper<in IN : FlickrImage, out OUT : FlickrImage> {

    fun map(image: IN): OUT
}