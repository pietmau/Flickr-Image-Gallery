package com.pppp.flickrimagegallery

import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.repository.FlickrRepository
import com.pppp.network.utils.Logger
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class RepositoryUseCase(
    private val flickrRepository: FlickrRepository,
    private val logger: Logger,
    override val coroutineContext: CoroutineDispatcher = Main
) : UseCase, CoroutineScope {

    private val TAG: String? = RepositoryUseCase::class.simpleName

    override fun execute(handler: (Event) -> Unit) {
        launch {
            try {
                val response: List<FlickrImage> = async { flickrRepository.getPics() }.await()
                handler(Event.LoadComplete(response))
            } catch (exception: Exception) {
                logger.w(TAG, exception.localizedMessage)
                handler(Event.Error(exception))
            }
        }
    }
}