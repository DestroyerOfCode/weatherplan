package com.babkovic.openweather.controller

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.openweather.service.OpenWeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@ResponseBody
class OpenWeatherControllerImpl(@Autowired private val service: OpenWeatherService) : OpenWeatherController {
    override fun test(): ResponseEntity<String> {
        return ok("test")
    }

    override fun getCurrentWeatherFromOpenWeatherByCityIds(): ResponseEntity<Flux<CurrentWeather>> {
        return ok(service.getCurrentWeatherFromOpenWeatherByCityIds())
    }

    override fun getCurrentWeatherFromOpenWeatherByCityId(
        lat: Double,
        lon: Double
    ): ResponseEntity<Mono<CurrentWeather>> {
        return ok(service.getCurrentWeatherFromOpenWeatherByCityId(lat, lon))
    }
}