package com.pppp.network.client

import com.pppp.entites.Feed
import kotlinx.coroutines.Deferred

interface Client {
    fun getPics(): Deferred<Feed>
}