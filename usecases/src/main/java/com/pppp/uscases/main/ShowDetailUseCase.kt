package com.pppp.uscases.main

import com.pppp.uscases.UseCase
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event

/** It actually does nothing, but I think that (to comply with how the framework works) a user interaction
 * should should go through the loop and get the chance to be evaluated by a use case */
object ShowDetailUseCase : UseCase<Effect.ShowDetail, Event> {
    override fun execute(effect: Effect.ShowDetail, handler: (Event) -> Unit) {
        handler(Event.ShowDetail(effect.detail))
    }
}