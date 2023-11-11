package com.babkovic.current.controller

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.service.ICurrentWeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class CurrentWeatherControllerImpl(@Autowired private val service: ICurrentWeatherService) : CurrentWeatherController {
    override fun saveCurrentWeather(): Flux<CurrentWeather> {
        return service.saveCurrentWeather()
    }

    override fun saveCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather> {
        return service.saveCurrentWeather(lat, lon)
    }
}