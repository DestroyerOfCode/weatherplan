package com.babkovic.current.model.domain

import com.babkovic.current.mapper.CurrentWeatherDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID


@JsonDeserialize(using = CurrentWeatherDeserializer::class, `as` = CurrentWeather::class)
@Document
data class CurrentWeather(
    @Id
    var id: UUID,
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var current: Current,
)
