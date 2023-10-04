package com.babkovic.openweather.controller

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.openweather.service.OpenWeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController

class OpenWeatherControllerImpl(@Autowired private val service: OpenWeatherService) : OpenWeatherController {

    override fun getCurrentWeatherFromOpenWeatherByCityIds(): Flux<CurrentWeather> {
        return service.getCurrentWeatherFromOpenWeatherByCityIds()
    }

    override fun getCurrentWeatherFromOpenWeatherByCityId(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): Mono<CurrentWeather> {
        return service.getCurrentWeatherFromOpenWeatherByCityId(lat, lon)
    }
}