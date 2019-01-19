package com.pppp.flickrimagegallery

import android.content.Context
import androidx.room.Room
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.mapper.MapperImpl
import com.pppp.network.api.Client
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
    fun provideUseCase(client: Client, context: Context): UseCase =
        RepositoryUseCase(
            client = client, database = Room.databaseBuilder(
                context,
                FlickrDataBase::class.java,
                "comics"
            ).build(),
            mapper = MapperImpl()
        )
}