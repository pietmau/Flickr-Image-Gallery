package com.pppp.network

import com.pppp.network.client.Client
import com.pppp.network.client.RetrofitClient
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideClient(): Client = RetrofitClient("https://api.flickr.com/")
}