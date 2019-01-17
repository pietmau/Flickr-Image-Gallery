package com.pppp.uscases.newstates

import com.spotify.mobius.Connection
import com.spotify.mobius.First
import com.spotify.mobius.Next
import com.spotify.mobius.functions.Consumer

fun update(model: Model, event: Event): Next<Model, Effect> =
    when (event) {
        is Event.LoadComplete -> Next.next(Model.Complete(event.results))
        else -> Next.next(model)
    }

fun effectHandler(eventConsumer: Consumer<Event>) = EffectHandler(eventConsumer) as Connection<Effect>

fun init(model: Model): First<Model, Effect> =
    when (model) {
        Model.Starting -> First.first(Model.Loading, mutableSetOf<Effect>(Effect.GetAllImages))
        else -> First.first(model)
    }

