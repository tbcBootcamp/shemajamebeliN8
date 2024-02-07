package com.example.shemajamebelin8.domain.usecase

import com.example.shemajamebelin8.data.global.common.Resource
import com.example.shemajamebelin8.domain.model.CitiesDomainModel
import com.example.shemajamebelin8.domain.repository.GlobalCitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCitiesFromApiUseCase @Inject constructor(private val remoteClothesRepository: GlobalCitiesRepository) {
    suspend operator fun invoke(): Flow<Resource<List<CitiesDomainModel>>> {
        return remoteClothesRepository.getCity()
    }
}
