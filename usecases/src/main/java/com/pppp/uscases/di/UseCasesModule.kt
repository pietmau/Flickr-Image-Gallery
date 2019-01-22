package com.pppp.uscases.di

import com.pppp.uscases.UseCase
import com.pppp.uscases.main.MainHandler
import com.pppp.uscases.main.ShowDetailUseCase
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import com.pppp.uscases.main.events.Model
import com.pppp.uscases.main.init
import com.pppp.uscases.main.update
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
class UseCasesModule {

    @JvmSuppressWildcards
    @Provides
    fun loopFactory(usecases: Map<Class<out Effect>, UseCase<Effect, Event>>): MobiusLoop.Builder<Model, Event, Effect> =
        Mobius.loop(::update) { consumer -> MainHandler(consumer, usecases) }.init(::init)

    @Provides
    @IntoMap
    @EffectKey(Effect.ShowDetail::class)
    fun provideUseCase(): UseCase<Effect, Event> = ShowDetailUseCase as UseCase<Effect, Event>


    @MapKey
    annotation class EffectKey(val value: KClass<out Effect>)
}
