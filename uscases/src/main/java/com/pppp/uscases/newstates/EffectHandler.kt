package com.pppp.uscases.newstates

import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

internal class EffectHandler(private val eventConsumer: Consumer<Event>) : Connection<Effect> {

    override fun accept(effect: Effect) {
        when (effect) {
            Effect.GetAllImages -> foo(eventConsumer)
        }
    }

    override fun dispose() {

    }

    fun foo(eventConsumer: Consumer<Event>) {
        Thread.sleep(5 * 1000)
        eventConsumer.accept(Event.LoadComplete(emptyList()))
    }
}
