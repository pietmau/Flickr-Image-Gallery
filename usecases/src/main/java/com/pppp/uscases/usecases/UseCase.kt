package com.pppp.uscases.usecases

import com.pppp.uscases.Event

interface UseCase {
    fun execute(function: (Event) -> Unit)
}
