package com.babkovic.current.service

import com.babkovic.current.model.domain.CurrentWeather
import reactor.core.publisher.Flux

interface WeatherCurrentService {

    fun fetchCurrentWeather(): Flux<CurrentWeather>
}
