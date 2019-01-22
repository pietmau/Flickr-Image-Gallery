package com.pppp.network.client

import com.pppp.network.pokos.RetrofitFeed
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("services/feeds/photos_public.gne")
    fun getPics(): Deferred<RetrofitFeed>
}