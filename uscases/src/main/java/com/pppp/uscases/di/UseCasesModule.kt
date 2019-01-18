package com.pppp.uscases.di

import com.pppp.uscases.*
import com.pppp.uscases.usecases.NetworkUseCase
import com.pppp.uscases.usecases.UseCases
import com.pppp.uscases.usecases.UseCasesImpl
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideUseCases(networkUseCase: NetworkUseCase): UseCases = UseCasesImpl(networkUseCase)

    @Provides
    fun loopFactory(usecases: UseCases): MobiusLoop.Builder<Model, Event, Effect> =
        Mobius.loop(::update) { consumer ->
            Handler(consumer, usecases)
        }.init(::init)
}
