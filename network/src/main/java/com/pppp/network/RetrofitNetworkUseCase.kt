package com.pppp.network


import com.pppp.network.api.Client
import com.pppp.network.poko.Feed
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.NetworkUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RetrofitNetworkUseCase(
    private val client: Client,
    private val background: CoroutineDispatcher = IO,
    override val coroutineContext: CoroutineDispatcher = Main
) : NetworkUseCase, CoroutineScope {

    override fun getAllImages(effect: Effect.GetAllImages, handler: (Event) -> Unit) {
        launch {
            try {
                val response: Feed = client.getPics().await()
                handler(Event.LoadComplete(response.entry ?: emptyList()))
            } catch (exception: Exception) {
                handler(Event.Error(exception))
            }
        }
    }

    /*fun getAllImagesNoCoroutines(effect: Effect.GetAllImages, handler: (Event) -> Unit) {
        client.getPics().enqueue(object : Callback<Feed> {
            override fun onFailure(call: Call<Feed>, t: Throwable) {
                handler(Event.Error(t))
            }

            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                handler(Event.LoadComplete(response.body()?.entry ?: emptyList()))
            }
        })
    }

    fun getAllImagesRxJava(effect: Effect.GetAllImages, handler: (Event) -> Unit) {

    }*/
}