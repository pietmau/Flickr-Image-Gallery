package com.pppp.flickrimagegallery.repository

import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.database.getLatestImages
import com.pppp.flickrimagegallery.database.insert
import com.pppp.flickrimagegallery.mapper.Mapper
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage
import com.pppp.network.client.Client
import com.pppp.network.client.logger.Logger
import com.pppp.network.pokos.RetrofitFlickrImage

internal const val MAX_PICS = 50

internal class FlickrRepositoryImpl(
    private val client: Client,
    private val database: FlickrDataBase,
    private val mapper: Mapper<FlickrImage, RoomFlickrImage>,
    private val logger: Logger
) : FlickrRepository {

    private val TAG = FlickrRepository::class.simpleName

    override suspend fun getPics(): List<FlickrImage> =
        try {
            val images = client.getPics().await().images ?: emptyList<RetrofitFlickrImage>()
            database.insert(images.map { it as RetrofitFlickrImage }.map { mapper.map(it) })
            images
        } catch (exception: Exception) {
            logger.w(TAG, exception.localizedMessage)
            database.getLatestImages(MAX_PICS)
        }
}