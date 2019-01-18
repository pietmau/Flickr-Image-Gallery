package com.pppp.network


import com.pppp.network.api.Client
import com.pppp.network.poko.RetrofitFeed
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.NetworkUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RetrofitNetworkUseCase(
    private val client: Client,
    override val coroutineContext: CoroutineDispatcher = Main
) : NetworkUseCase, CoroutineScope {

    override fun getAllImages(effect: Effect.GetAllImages, handler: (Event) -> Unit) {
        launch {
            try {
                val response: RetrofitFeed = client.getPics().await()
                val results= response.entry ?: emptyList()
                handler(Event.LoadComplete(results))
            } catch (exception: Exception) {
                handler(Event.Error(exception))
            }
        }
    }
}