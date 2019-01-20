package com.pppp.flickrimagegallery

import com.pppp.entites.FlickrImage

interface Repository {
    suspend fun getPics(): List<FlickrImage>
}
