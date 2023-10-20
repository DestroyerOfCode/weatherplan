package com.babkovic.current.service

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface WeatherCurrentService {

    fun fetchCurrentWeather(): Flux<CurrentWeather>
    fun fetchCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather>
    fun saveCurrentWeather(): Flux<CurrentWeather>
    fun saveCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather>
    fun test(): ResponseEntity<String>
}
