package com.pppp.uscases.usecases

import com.pppp.uscases.Event

interface NetworkUseCase {
    fun getAllImages(function: (Event) -> Unit)
}
