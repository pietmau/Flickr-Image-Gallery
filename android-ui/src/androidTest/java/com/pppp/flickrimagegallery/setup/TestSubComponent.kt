package com.pppp.flickrimagegallery.setup

import com.pppp.flickrimagegallery.features.main.view.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface TestSubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}