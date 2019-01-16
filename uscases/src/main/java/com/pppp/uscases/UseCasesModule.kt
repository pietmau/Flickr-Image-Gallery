package com.pppp.uscases

import com.pppp.uscases.usecases.UseCases
import com.pppp.uscases.usecases.UseCasesImpl
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideUseCases(): UseCases = UseCasesImpl()

}