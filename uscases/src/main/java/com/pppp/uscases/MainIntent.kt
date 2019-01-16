package com.pppp.uscases

sealed class MainIntent {

    data class GetDetail(val data: Any) : MainIntent()
}