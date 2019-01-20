package com.pppp.uscases.di

import com.pppp.uscases.Effect
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class EffectKey(val value: KClass<out Effect>)