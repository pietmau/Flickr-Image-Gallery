package com.pppp.network

import com.pppp.network.api.RetrofitClient
import com.pppp.uscases.usecases.NetworkUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class NetworkModule {

    @Inject
    @Provides
    fun provideNetwork(): NetworkUseCase = RetrofitNetworkUseCase(RetrofitClient)
}