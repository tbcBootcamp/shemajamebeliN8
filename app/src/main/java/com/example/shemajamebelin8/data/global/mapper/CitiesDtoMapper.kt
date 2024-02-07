package com.example.shemajamebelin8.data.global.mapper

import com.example.shemajamebelin8.data.global.model.CitiesDto
import com.example.shemajamebelin8.domain.model.CitiesDomainModel

fun CitiesDto.toDomain() =
    CitiesDomainModel(
        id = id,
        cover = cover,
        price = price,
        title = title,
        location = location,
        reactionCount = reactionCount,
        rate=rate
    )

