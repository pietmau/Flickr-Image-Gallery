package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.spotify.mobius.functions.Consumer

interface UseCases {
    fun getAllImages(effect: Effect.GetAllImages, consumer: Consumer<Event>)
}