package com.pppp.uscases.usecases

import com.pppp.uscases.Event

interface UseCase {
    fun accept(function: (Event) -> Unit)
}
