package com.example.shemajamebelin8.data.global.model

import com.squareup.moshi.Json

data class CitiesDto(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val location: String,
    @Json(name = "reaction_count")
    val reactionCount: Int,
    val rate: Int?
)

