package com.pppp.flickrimagegallery.features.main.view.controller

interface Controller<M, E> {
    fun stop()
    fun start()
    fun disconnect()
    fun connect(accept: ((M) -> Unit)? = null, dispose: (() -> Unit)? = null)
    fun accept(event: E)
}
