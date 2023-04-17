package com.example.mobileapptest.di.modules

import com.example.mobileapptest.data.network.IRegistrationAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Kevel on 4/15/2023.
 */

/**
 * Following the Singleton design pattern, we created a API singleton to be able to use it anywhere in the app where it is injected.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://platon.cf-it.at/affiliate/")
        .build()

    @Provides
    @Singleton
    fun provideRegistrationApi(retrofit: Retrofit): IRegistrationAPI = retrofit.create(IRegistrationAPI::class.java)
}