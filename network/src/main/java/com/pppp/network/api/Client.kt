package com.pppp.network.api

import com.pppp.entites.Feed
import kotlinx.coroutines.Deferred

interface Client {
    fun getPics(): Deferred<Feed>
}