package com.pppp.network.api

import com.pppp.network.poko.RetrofitFeed
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("services/feeds/photos_public.gne")
    fun getPics(): Deferred<RetrofitFeed>
}