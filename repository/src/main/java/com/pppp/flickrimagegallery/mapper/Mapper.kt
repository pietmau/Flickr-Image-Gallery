package com.pppp.flickrimagegallery.mapper

import com.pppp.entites.FlickrImage

internal interface Mapper<IN : FlickrImage, OUT : FlickrImage> {

    fun map(image: IN): OUT
}