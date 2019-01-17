package com.pppp.network

import com.pppp.network.api.RetrofitClient
import com.pppp.uscases.usecases.NetworkUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

@Module
class NetworkModule {

    @Inject
    @Provides
    fun provideNetwork(): NetworkUseCase = RetrofitNetworkUseCase(RetrofitClient(), IO, Main)
}