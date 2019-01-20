package com.pppp.uscases

import com.spotify.mobius.First
import com.spotify.mobius.Next

fun update(model: Model, event: Event): Next<Model, Effect> =
    when (event) {
        is Event.LoadComplete -> {
            val complete = Model.Complete(event.images)
            val effects = mutableSetOf(Effect.GotImages(event.images))
            Next.next(complete, effects)
        }
        else -> Next.noChange()
    }

fun init(model: Model): First<Model, Effect> =
    when (model) {
        Model.Starting -> First.first(Model.Loading, mutableSetOf<Effect>(Effect.GetAllImages))
        else -> First.first(model)
    }
