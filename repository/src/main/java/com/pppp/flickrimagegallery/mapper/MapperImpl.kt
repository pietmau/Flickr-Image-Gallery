package com.pppp.flickrimagegallery.mapper

import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage

internal class MapperImpl : Mapper<FlickrImage, RoomFlickrImage> {

    override fun map(image: FlickrImage): RoomFlickrImage =
        RoomFlickrImage(id = image.id, title = image.title, imageUrl = image.imageUrl)
}