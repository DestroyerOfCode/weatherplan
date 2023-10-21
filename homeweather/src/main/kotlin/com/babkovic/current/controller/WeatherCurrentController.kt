package com.babkovic.current.controller

import com.babkovic.current.model.domain.CurrentWeather
import org.jetbrains.annotations.NotNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/current")
@ResponseBody
interface WeatherCurrentController {
    @PostMapping("/save")
    fun saveCurrentWeather(
        @NotNull @RequestParam lat: Double,
        @NotNull @RequestParam lon: Double
    ): Mono<CurrentWeather>
    @PostMapping("/bulk/save")
    fun saveCurrentWeather(): Flux<CurrentWeather>
}