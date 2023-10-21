package com.babkovic.openweather.model.domain

import com.babkovic.openweather.mapper.CityDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = CityDeserializer::class)
data class City(
    val id: Long,
    val name: String,
    val state: String,
    val country: String,
    val coord: Coord
)