package com.pppp.network.api

import com.pppp.network.poko.Result
import retrofit2.Call

interface Client {
    fun getPics(): Call<Result>
}