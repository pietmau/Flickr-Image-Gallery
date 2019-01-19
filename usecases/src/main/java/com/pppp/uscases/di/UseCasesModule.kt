package com.pppp.uscases.di

import com.pppp.uscases.*
import com.pppp.uscases.usecases.UseCase
import com.pppp.uscases.usecases.UseCases
import com.pppp.uscases.usecases.UseCasesImpl
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @JvmSuppressWildcards
    @Provides
    fun provideUseCases(useCase: Map<Class<out Effect>, UseCase>): UseCases = UseCasesImpl(useCase)

    @Provides
    fun loopFactory(usecases: UseCases): MobiusLoop.Builder<Model, Event, Effect> =
        Mobius.loop(::update) { consumer -> Handler(consumer, usecases) }.init(::init)


}
