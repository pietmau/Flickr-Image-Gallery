package com.pppp.network.api

import com.pppp.network.poko.Feed
import kotlinx.coroutines.Deferred

interface Client {
    fun getPics(): Deferred<Feed>
}