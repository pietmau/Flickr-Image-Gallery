package com.pppp.flickrimagegallery

import com.pppp.uscases.Effect
import com.pppp.uscases.di.EffectKey
import com.pppp.uscases.usecases.UseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object RepositoryModule {

    @JvmStatic
    @Provides
    @IntoMap
    @EffectKey(Effect.GetAllImages::class)
    fun provideUseCase(repo: Repository): UseCase = RepositoryUseCase(repo)
}