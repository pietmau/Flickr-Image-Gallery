package com.pppp.network.api

import com.pppp.network.poko.Feed
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("services/feeds/photos_public.gne")
    fun getPics(): Call<Feed>
}