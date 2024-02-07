package com.example.shemajamebelin8.data.global.service

import com.example.shemajamebelin8.data.global.model.CitiesDto
import retrofit2.Response
import retrofit2.http.GET



interface CitiesApi {
    @GET("0545ddc1-c487-46ce-b70c-5b95270d6b76")
    suspend fun getClothes(): Response<List<CitiesDto>>
}
