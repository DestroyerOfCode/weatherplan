package com.babkovic.current.service

import com.babkovic.current.model.domain.CurrentWeather
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface WeatherCurrentService {

    fun fetchCurrentWeather(): Flux<CurrentWeather>
    fun fetchCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather>
}
