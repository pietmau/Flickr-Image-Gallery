package com.pppp.uscases

sealed class ViewState {
    object Loading : ViewState()
    data class Content(val result: List<Any>) : ViewState()
    data class Error(val exception: Exception) : ViewState()
}
