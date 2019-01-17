package com.pppp.uscases

import com.spotify.mobius.First
import com.spotify.mobius.Next

fun update(model: Model, event: Event): Next<Model, Effect> =
    when (event) {
        is Event.LoadComplete -> Next.next(Model.Complete(event.results))
        else -> Next.next(model)
    }

fun init(model: Model): First<Model, Effect> =
    when (model) {
        Model.Starting -> First.first(
            Model.Loading, mutableSetOf<Effect>(
                Effect.GetAllImages
            ))
        else -> First.first(model)
    }

