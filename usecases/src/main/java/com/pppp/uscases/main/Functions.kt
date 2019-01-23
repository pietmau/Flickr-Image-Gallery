package com.pppp.uscases.main

import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import com.pppp.uscases.main.events.Model
import com.spotify.mobius.First
import com.spotify.mobius.Next

fun update(model: Model, event: Event): Next<Model, Effect> {
    return when (event) {
        is Event.LoadComplete -> onComplete(event)
        is Event.DetailSelected -> onDetailSelected(model, event)
        is Event.ShowDetail -> Next.next(Model.NavigateToDetail(event.detail, model))
        else -> Next.noChange()
    }
}

private fun onDetailSelected(model: Model, event: Event.DetailSelected): Next<Model, Effect> =
    if (model is Model.Complete) {
        Next.next(model, setOf(Effect.ShowDetail(event.detail)))
    } else {
        Next.noChange()
    }


fun init(model: Model): First<Model, Effect> =
    when (model) {
        Model.Starting -> First.first(
            Model.Loading, mutableSetOf<Effect>(
                Effect.GetAllImages
            )
        )
        is Model.NavigateToDetail -> First.first(model.previousState)
        is Model.Warning -> First.first(model.previousState)
        else -> First.first(model)
    }

private fun onComplete(event: Event.LoadComplete): Next<Model, Effect> {
    val complete = Model.Complete(event.images)
    val effects = mutableSetOf(Effect.GotImages(event.images))
    return Next.next(complete, effects)
}


