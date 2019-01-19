package com.pppp.flickrimagegallery.features.main.view.controller

import com.spotify.mobius.Connection
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.functions.Consumer

internal class MobiusController<M, E>(private val mobius: MobiusLoop.Controller<M, E>) :
    Controller<M, E> {

    private var consumer: Consumer<E>? = null

    override fun connect(accept: ((M) -> Unit)?, dipose: (() -> Unit)?) {
        mobius.connect { consumer ->
            this@MobiusController.consumer = consumer
            object : Connection<M> {
                override fun accept(value: M) {
                    accept?.invoke(value)
                }

                override fun dispose() {
                    dipose?.invoke()
                }
            }
        }
    }

    override fun stop() {
        mobius.stop()
    }

    override fun start() {
        mobius.start()
    }

    override fun disconnect() {
        mobius.disconnect()
    }

    override fun accept(event: E) {
        consumer?.accept(event)
    }

}

