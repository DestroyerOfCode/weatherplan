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

    @GetMapping("/test", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun test(): ResponseEntity<String>

    @GetMapping("/all")
    fun fetchCurrentWeather(): Flux<CurrentWeather>

    @GetMapping
    fun fetchCurrentWeather(
        @NotNull @RequestParam lat: Double,
        @NotNull @RequestParam lon: Double
    ): Mono<CurrentWeather>

    @PostMapping("/save")
    fun saveCurrentWeather(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): Mono<CurrentWeather>

    @PostMapping("/bulk/save")
    fun saveCurrentWeather(): Flux<CurrentWeather>
}