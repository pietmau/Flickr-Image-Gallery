package com.pppp.network.api

import com.pppp.network.poko.RetrofitFeed
import kotlinx.coroutines.Deferred

interface Client {
    fun getPics(): Deferred<RetrofitFeed>
}