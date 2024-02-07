package com.example.shemajamebelin8.domain.repository

import com.example.shemajamebelin8.data.global.common.Resource
import com.example.shemajamebelin8.domain.model.CitiesDomainModel
import kotlinx.coroutines.flow.Flow

interface GlobalCitiesRepository {
    suspend fun getCity(): Flow<Resource<List<CitiesDomainModel>>>
}