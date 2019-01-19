package com.pppp.flickrimagegallery

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.database.insert
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
import kotlinx.coroutines.withContext

internal class RepositoryUseCase(
    private val client: Client,
    private val database: FlickrDataBase,
    private val mapper: Mapper<FlickrImage, RoomFlickrImage>,
    private val maiThreadDispatcher: CoroutineDispatcher = Main,
    override val coroutineContext: CoroutineDispatcher = IO
) : UseCase, CoroutineScope {

    override fun execute(handler: (Event) -> Unit) {
        launch {
            try {
                val response: Feed = client.getPics().await()
                val results = response.entry ?: emptyList()//TODO use interceptor instead
                val mapped = results.map { mapper.map(it) }
                database.insert(mapped)
                toUi {
                    handler(Event.LoadComplete(results))
                }
            } catch (exception: Exception) {
                toUi {
                    handler(Event.Error(exception))
                }
            }
        }
    }

    suspend private fun <T> toUi(block: suspend CoroutineScope.() -> T) {
        withContext(maiThreadDispatcher, block)
    }
}