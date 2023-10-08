package com.babkovic.current.controller

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.service.WeatherCurrentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class WeatherCurrentControllerImpl(@Autowired private val service: WeatherCurrentService) : WeatherCurrentController {
    override fun fetchCurrentWeather(): Flux<CurrentWeather> {
        return service.fetchCurrentWeather()
    }

    override fun fetchCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather> {
        return service.fetchCurrentWeather(lat, lon)
    }
}