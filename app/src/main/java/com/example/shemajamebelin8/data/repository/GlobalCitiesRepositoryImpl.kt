package com.example.shemajamebelin8.data.repository

import com.example.shemajamebelin8.data.global.common.HandleResponse
import com.example.shemajamebelin8.data.global.common.Resource
import com.example.shemajamebelin8.data.global.mapper.asResource
import com.example.shemajamebelin8.data.global.mapper.toDomain
import com.example.shemajamebelin8.data.global.service.CitiesApi
import com.example.shemajamebelin8.domain.model.CitiesDomainModel
import com.example.shemajamebelin8.domain.repository.GlobalCitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GlobalCitiesRepositoryImpl  @Inject constructor(
    private val citiesApiService: CitiesApi,
    private val handleResponse: HandleResponse
) : GlobalCitiesRepository {

    override suspend fun getCity(): Flow<Resource<List<CitiesDomainModel>>> =
        handleResponse.safeApiCall {
            citiesApiService.getClothes()
        }.asResource { clothes ->
            clothes.map { it.toDomain() }
        }
}