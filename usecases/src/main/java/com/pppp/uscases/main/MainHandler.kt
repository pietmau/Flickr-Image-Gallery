package com.pppp.uscases.main

import com.pppp.uscases.UseCase
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class MainHandler(
    private val consumer: Consumer<Event>,
    private val usecases: Map<Class<out Effect>, UseCase<Effect, Event>>
) : Connection<Effect> {

    override fun accept(effect: Effect) {
        usecases[effect::class.java]?.execute(effect) { event ->
            consumer.accept(event)
        }
    }

    override fun dispose() {
        /* NoOp */
    }
}