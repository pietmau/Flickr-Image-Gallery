package com.pppp.uscases.usecases.showDetail

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.UseCase

object ShowDetailUseCase : UseCase<Effect.ShowDetail> {
    override fun execute(effect: Effect.ShowDetail, handler: (Event) -> Unit) {
        if (effect.imageLoaded) {
            handler(Event.ShowDetail(effect.detail))
        } else {//TODO
        }
    }
}