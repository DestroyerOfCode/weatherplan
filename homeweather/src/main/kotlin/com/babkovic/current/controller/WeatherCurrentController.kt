package com.babkovic.current.controller

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/current")
interface WeatherCurrentController {

    @GetMapping
    fun fetchCurrentWeather(): Flux<CurrentWeather>

    @GetMapping("/getByCoords")
    fun fetchCurrentWeather(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): Mono<CurrentWeather>

    @PostMapping("/saveByCoords")
    fun saveCurrentWeather(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    )
}