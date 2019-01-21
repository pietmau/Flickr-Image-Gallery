package com.pppp.uscases

import com.spotify.mobius.First
import com.spotify.mobius.Next

fun update(model: Model, event: Event): Next<Model, Effect> =
    when (event) {
        is Event.LoadComplete -> onComplete(event)
        is Event.DetailClicked -> onShowDetail(event, model)
        else -> Next.noChange()
    }

fun init(model: Model): First<Model, Effect> =
    when (model) {
        Model.Starting -> First.first(Model.Loading, mutableSetOf<Effect>(Effect.GetAllImages))
        is Model.NavigateToDetail -> First.first(model.previousState)
        else -> First.first(model)
    }

private fun onComplete(event: Event.LoadComplete): Next<Model, Effect> {
    val complete = Model.Complete(event.images)
    val effects = mutableSetOf(Effect.GotImages(event.images))
    return Next.next(complete, effects)
}

fun onShowDetail(event: Event.DetailClicked, lastModel: Model): Next<Model, Effect> {
    val detail = Model.NavigateToDetail.Detail(event.item, event.position)
    val model = Model.NavigateToDetail(detail = detail, previousState = lastModel)
    return Next.next(model)
}
