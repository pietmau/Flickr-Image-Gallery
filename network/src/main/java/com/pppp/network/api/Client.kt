package com.pppp.network.api

import com.pppp.network.poko.Feed
import retrofit2.Call

interface Client {
    fun getPics(): Call<Feed>
}