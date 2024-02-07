package com.example.shemajamebelin8.domain.model

data class CitiesDomainModel(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val location: String,
    val reactionCount: Int,
    val rate: Int?
)