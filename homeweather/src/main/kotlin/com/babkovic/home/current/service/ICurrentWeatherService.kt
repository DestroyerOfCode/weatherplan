package com.babkovic.home.current.service

import com.babkovic.home.current.model.domain.CurrentWeather
import org.jetbrains.annotations.NotNull
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ICurrentWeatherService {
    fun saveCurrentWeather(): Flux<CurrentWeather>
    fun saveCurrentWeather(@NotNull lat: Double, @NotNull lon: Double): Mono<CurrentWeather>
}