package com.babkovic.openweather.service

import com.babkovic.current.model.domain.CurrentWeather
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface OpenWeatherService {

    fun getCurrentWeatherFromOpenWeatherByCityIds(): Flux<CurrentWeather>
    fun getCurrentWeatherFromOpenWeatherByCityId(lat: Double, lon: Double): Mono<CurrentWeather>
}
