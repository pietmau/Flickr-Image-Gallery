package com.pppp.network


import com.pppp.entites.Feed
import com.pppp.network.api.Client
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RetrofitUseCase(
    private val client: Client,
    override val coroutineContext: CoroutineDispatcher = Main
) : UseCase, CoroutineScope {

    override fun accept(handler: (Event) -> Unit) {
        launch {
            try {
                val response: Feed = client.getPics().await()
                val results = response.entry ?: emptyList()//TODO use interceptor instead
                handler(Event.LoadComplete(results))
            } catch (exception: Exception) {
                handler(Event.Error(exception))
            }
        }
    }
}