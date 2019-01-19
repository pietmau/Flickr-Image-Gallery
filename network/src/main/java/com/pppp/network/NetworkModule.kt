package com.pppp.network

import com.pppp.network.api.RetrofitClient
import com.pppp.uscases.Effect
import com.pppp.uscases.di.EffectKey
import com.pppp.uscases.usecases.UseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers.Main

@Module
class NetworkModule {

    @Provides
    @IntoMap
    @EffectKey(Effect.GetAllImages::class)
    fun provideNetwork(): UseCase =
        RetrofitUseCase(RetrofitClient("https://api.flickr.com/"), Main)
}