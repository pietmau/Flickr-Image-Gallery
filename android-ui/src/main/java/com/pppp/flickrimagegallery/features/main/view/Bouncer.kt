package com.pppp.flickrimagegallery.features.main.view

/** We need this to debounce in some cases multiple clicks on the recycler elements
 * RxJava would have helped */
interface Bouncer {
    fun runOrBounce(function: () -> Unit)
}
