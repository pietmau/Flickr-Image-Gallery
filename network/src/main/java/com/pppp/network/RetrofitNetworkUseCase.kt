package com.pppp.network

import com.pppp.network.api.Client
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.NetworkUseCase

class RetrofitNetworkUseCase constructor(val client: Client) : NetworkUseCase {

    override fun getAllImages(effect: Effect.GetAllImages, function: (Event) -> Unit) {

    }
}