package com.pppp.flickrimagegallery

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.database.FlickrDatabase
import com.pppp.flickrimagegallery.mapper.Mapper
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage
import com.pppp.network.api.Client
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

internal class RepositoryUseCase(
    private val client: Client,
    private val database: FlickrDatabase,
    private val mapper: Mapper<FlickrImage, RoomFlickrImage>,
    private val maiThreadDispatcher: CoroutineDispatcher = Main,
    override val coroutineContext: CoroutineDispatcher = IO
) : UseCase, CoroutineScope {

    override fun accept(handler: (Event) -> Unit) {
        launch {
            try {
                val response: Feed = client.getPics().await()
                val results = response.entry ?: emptyList()//TODO use interceptor instead
                val mapped = results.map { mapper.map(it) }
                database.insert(mapped)
                handler(Event.LoadComplete(results))
            } catch (exception: Exception) {
                handler(Event.Error(exception))
            }
        }
    }
}