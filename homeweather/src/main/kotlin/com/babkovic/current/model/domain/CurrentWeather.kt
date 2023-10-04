package com.babkovic.current.model.domain

import com.babkovic.current.mapper.CurrentWeatherDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize


@JsonDeserialize(using = CurrentWeatherDeserializer::class, `as` = CurrentWeather::class)
data class CurrentWeather(
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var current: Current,
)
