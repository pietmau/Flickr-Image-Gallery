package com.pppp.flickrimagegallery

import com.pppp.entites.FlickrImage
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class RepositoryUseCase(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = IO,
    override val coroutineContext: CoroutineDispatcher = Main
) : UseCase, CoroutineScope {

    override fun execute(handler: (Event) -> Unit) {
        launch {
            try {
                val response: List<FlickrImage> = async { repository.getPics() }.await()
                handler(Event.LoadComplete(response))
            } catch (exception: Exception) {
                handler(Event.Error(exception))
            }
        }
    }
}