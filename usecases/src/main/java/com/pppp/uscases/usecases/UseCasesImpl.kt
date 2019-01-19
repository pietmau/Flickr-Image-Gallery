package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.spotify.mobius.functions.Consumer

class UseCasesImpl(private val networkUseCase: NetworkUseCase) : UseCases {

    override fun getAllImages(effect: Effect.GetAllImages, consumer: Consumer<Event>) {
        networkUseCase.getAllImages() { event: Event ->
            consumer.accept(event)
        }
    }

}