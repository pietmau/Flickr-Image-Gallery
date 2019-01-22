package com.pppp.flickrimagegallery.di

import android.content.Context
import androidx.room.Room
import com.pppp.flickrimagegallery.RepositoryUseCase
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.mapper.MapperImpl
import com.pppp.flickrimagegallery.repository.FlickrRepository
import com.pppp.flickrimagegallery.repository.FlickrRepositoryImpl
import com.pppp.network.client.Client
import com.pppp.network.client.logger.AndroidLogger
import com.pppp.uscases.UseCase
import com.pppp.uscases.di.UseCasesModule
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object RepositoryModule {

    @JvmStatic
    @Provides
    @IntoMap
    @UseCasesModule.EffectKey(Effect.GetAllImages::class)
    fun provideUseCase(repo: FlickrRepository): UseCase<Effect, Event> =
        RepositoryUseCase(repo, AndroidLogger()) as UseCase<Effect, Event>

    @Provides
    @JvmStatic
    internal fun repo(client: Client, db: FlickrDataBase): FlickrRepository =
        FlickrRepositoryImpl(client, db, MapperImpl(), AndroidLogger())

    @Provides
    @JvmStatic
    internal fun db(context: Context): FlickrDataBase =
        Room.databaseBuilder(context, FlickrDataBase::class.java, "images").build()
}