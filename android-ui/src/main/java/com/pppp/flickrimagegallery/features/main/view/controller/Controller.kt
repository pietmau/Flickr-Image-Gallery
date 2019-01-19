package com.pppp.flickrimagegallery.features.main.view.controller

interface Controller<M, E> {
    fun stop()
    fun start()
    fun disconnect()
    fun connect(accep: ((M) -> Unit)? = null, dis: (() -> Unit)? = null)
    fun accept(event: E)
}