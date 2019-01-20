package com.pppp.uscases

import com.pppp.uscases.usecases.UseCases
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class Handler(
    private val consumer: Consumer<Event>,
    private val usecases: UseCases
) : Connection<Effect> {

    override fun accept(effect: Effect) = usecases.accept(effect, consumer)

    override fun dispose() {
        /* NoOp */
    }
}