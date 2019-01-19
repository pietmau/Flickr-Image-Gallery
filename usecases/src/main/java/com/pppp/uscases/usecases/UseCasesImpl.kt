package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.spotify.mobius.functions.Consumer

class UseCasesImpl(private val useCases: Map<Class<out Effect>, UseCase>) : UseCases {

    override fun accept(effect: Effect, consumer: Consumer<Event>) {
        useCases.get(effect::class.java)?.execute { event ->
            consumer.accept(event)
        }
    }
}