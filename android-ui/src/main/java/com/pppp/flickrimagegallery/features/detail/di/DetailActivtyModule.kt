package com.pppp.flickrimagegallery.features.main.di

import com.pppp.flickrimagegallery.features.detail.view.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailActivtyModule {
    @ContributesAndroidInjector
    abstract fun provideDetailActivity(): DetailActivity
}
