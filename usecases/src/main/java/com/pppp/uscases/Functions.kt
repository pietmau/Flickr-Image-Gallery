package com.pppp.uscases

import com.spotify.mobius.First
import com.spotify.mobius.Next

fun update(model: Model, event: Event): Next<Model, Effect> =
    when (event) {
        is Event.LoadComplete -> onComplete(event)
        is Event.DetailSelected -> Next.next(model, setOf(Effect.ShowDetail(event.detail)))
        is Event.ShowDetail -> Next.next(Model.NavigateToDetail(event.detail, model))
        else -> Next.noChange()
    }

fun init(model: Model): First<Model, Effect> =
    when (model) {
        Model.Starting -> First.first(Model.Loading, mutableSetOf<Effect>(Effect.GetAllImages))
        is Model.NavigateToDetail -> First.first(model.previousState)
        is Model.Warning -> First.first(model.previousState)
        else -> First.first(model)
    }

private fun onComplete(event: Event.LoadComplete): Next<Model, Effect> {
    val complete = Model.Complete(event.images)
    val effects = mutableSetOf(Effect.GotImages(event.images))
    return Next.next(complete, effects)
}


