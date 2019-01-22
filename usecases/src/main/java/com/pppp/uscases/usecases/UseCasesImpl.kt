package com.pppp.uscases.usecases

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.spotify.mobius.functions.Consumer

class UseCasesImpl(private val useCases: Map<Class<out Effect>, UseCase<Effect>>) : UseCases {

    override fun accept(effect: Effect, consumer: Consumer<Event>) {
        useCases[effect::class.java]?.execute(effect) { event ->
            consumer.accept(event)
        }
    }
}