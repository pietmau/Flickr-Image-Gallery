package com.pppp.flickrimagegallery.repository

import com.pppp.entites.FlickrImage

interface FlickrRepository {
    suspend fun getPics(): List<FlickrImage>
}
