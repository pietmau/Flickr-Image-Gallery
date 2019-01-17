package com.pppp.uscases

sealed class Effect {
    object GetAllImages : Effect()
    data class ShowDetail(val detail: Any) : Effect()
}