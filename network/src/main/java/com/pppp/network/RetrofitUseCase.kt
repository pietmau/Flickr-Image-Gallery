package com.pppp.network


import com.pppp.entites.Feed
import com.pppp.network.api.Client
import com.pppp.network.utils.Logger
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RetrofitUseCase(
    private val client: Client,
    private val logger: Logger,
    override val coroutineContext: CoroutineDispatcher = Main
) : UseCase, CoroutineScope {
    private val TAG = RetrofitUseCase::class.simpleName

    override fun execute(handler: (Event) -> Unit) {
        launch {
            try {
                val response: Feed = client.getPics().await()
                val results = response.entry ?: emptyList()//TODO use interceptor instead
                handler(Event.LoadComplete(results))
            } catch (exception: Exception) {
                logger.w(TAG, exception.localizedMessage)
                handler(Event.Error(exception))
            }
        }
    }
}