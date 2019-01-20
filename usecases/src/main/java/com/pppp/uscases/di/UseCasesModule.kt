package com.pppp.uscases.di

import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import com.pppp.uscases.Handler
import com.pppp.uscases.Model
import com.pppp.uscases.init
import com.pppp.uscases.update
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
