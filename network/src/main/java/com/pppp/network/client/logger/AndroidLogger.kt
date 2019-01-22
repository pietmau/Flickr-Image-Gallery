package com.pppp.network.client.logger

import android.util.Log
import javax.inject.Inject

class AndroidLogger @Inject constructor() : Logger {
    override fun w(tag: String?, message: String?) {
        Log.w(tag ?: "Error", message ?: "Error")
    }
}
