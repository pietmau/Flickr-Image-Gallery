package com.pppp.network

import android.util.Log
import com.pppp.network.api.Client
import com.pppp.network.poko.Feed
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.usecases.NetworkUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitNetworkUseCase constructor(val client: Client) : NetworkUseCase {

    override fun getAllImages(effect: Effect.GetAllImages, function: (Event) -> Unit) {
        client.getPics().enqueue(object : Callback<Feed>{
            override fun onFailure(call: Call<Feed>, t: Throwable) {
                Log.e("fff", "failure")
            }

            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                Log.e("fff", "Success")
            }
        })
    }
}