package com.example.shemajamebelin8.presentation.mapper

import com.example.shemajamebelin8.domain.model.CitiesDomainModel
import com.example.shemajamebelin8.presentation.model.CitiesUiModel

fun CitiesDomainModel.toPresentation() = CitiesUiModel(
    id = id,
    cover = cover,
    price = price,
    title = title,
    location = location,
    reactionCount = reactionCount,
    rate=rate
)

fun CitiesUiModel.toDomain() = CitiesDomainModel(
    id = id,
    cover = cover,
    price = price,
    title = title,
    location = location,
    reactionCount = reactionCount,
    rate=rate
)