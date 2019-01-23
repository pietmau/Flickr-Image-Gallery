package com.pppp.flickrimagegallery.features.main.view

class BouncerImpl(private val delayInMills: Int = 20) : Bouncer {
    private var time = System.currentTimeMillis()

    override fun runOrBounce(function: () -> Unit) {
        val timeElapsed = System.currentTimeMillis() - time
        if (timeElapsed > delayInMills) {
            function.invoke()
        }
        time = System.currentTimeMillis()
    }
}