package com.example.shemajamebelin8.di

import com.example.shemajamebelin8.data.global.common.HandleResponse
import com.example.shemajamebelin8.data.global.service.CitiesApi
import com.example.shemajamebelin8.data.repository.GlobalCitiesRepositoryImpl
import com.example.shemajamebelin8.domain.repository.GlobalCitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteClothesRepository(
        citiesApiService: CitiesApi,
        handleResponse: HandleResponse
    ): GlobalCitiesRepository =
        GlobalCitiesRepositoryImpl(
            citiesApiService = citiesApiService,
            handleResponse = handleResponse,
        )
}