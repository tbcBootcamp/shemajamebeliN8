package com.example.shemajamebelin8.data.global.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

class HandleResponse {
    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(response = body))
            } else {
                throw HttpException(response)
            }
        } catch (t: Throwable) {
            emit(Resource.Error(message = AppError.fromException(t).message, throwable = t))
        }finally {
            emit(Resource.Loading(false))
        }
    }
}