package com.pppp.uscases.newstates

sealed class Effect {
    object GetAllImages : Effect()
    data class ShowDetail(val detail: Any) : Effect()
}