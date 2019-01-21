package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event

interface UseCase<in T : Effect> {
    fun execute(effect: T, callback: (Event) -> Unit)
}
