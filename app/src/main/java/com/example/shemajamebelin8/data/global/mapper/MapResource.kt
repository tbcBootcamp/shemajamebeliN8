package com.example.shemajamebelin8.data.global.mapper

import com.example.shemajamebelin8.data.global.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T : Any, R : Any> Flow<Resource<T>>.asResource(mapper: (T) -> R): Flow<Resource<R>> {
    return this.map { resource ->
        when (resource) {
            is Resource.Success -> Resource.Success(mapper(resource.response))
            is Resource.Error -> Resource.Error(resource.message, resource.throwable)
            is Resource.Loading -> Resource.Loading(resource.loading)
        }
    }
}