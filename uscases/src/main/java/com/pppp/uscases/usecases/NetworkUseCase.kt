package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event

interface NetworkUseCase {
    fun getAllImages(effect: Effect.GetAllImages, function: (Event) -> Unit)
}
