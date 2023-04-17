package com.example.mobileapptest.di.modules

import com.example.mobileapptest.data.network.RegistrationApiRepository
import com.example.mobileapptest.domain.contracts.IRegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Kevel on 4/16/2023.
 */

/**
 * Following the Singleton design pattern, we created a repository singleton to be able to use it anywhere in the app where it is injected.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRegistrationRepository(implementation: RegistrationApiRepository): IRegistrationRepository
}