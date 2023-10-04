package com.babkovic.current.controller

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.service.WeatherCurrentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class WeatherCurrentControllerImpl(@Autowired private val service: WeatherCurrentService) : WeatherCurrentController {
    override fun fetchCurrentWeather(): Flux<CurrentWeather> {
        return service.fetchCurrentWeather()
    }
}