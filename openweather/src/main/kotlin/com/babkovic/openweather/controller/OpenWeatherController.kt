package com.babkovic.openweather.controller

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/open/current", produces = [MediaType.APPLICATION_NDJSON_VALUE])
interface OpenWeatherController {

    @GetMapping
    fun getCurrentWeatherFromOpenWeatherByCityIds(): Flux<CurrentWeather>

    @GetMapping("getByCoords")
    @ResponseBody
    fun getCurrentWeatherFromOpenWeatherByCityId(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): Mono<CurrentWeather>
}
