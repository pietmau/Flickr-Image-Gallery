package com.pppp.network.api

import retrofit2.Retrofit


object RetrofitClient : Client {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/")
        .build()
    val service = retrofit.create(Api::class.java)

    override fun getPics() = service.getPics()
}