package com.babkovic.home.current.controller

import com.babkovic.home.current.model.domain.CurrentWeather
import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/api/home/current")
@ResponseBody
interface CurrentWeatherController {
    @PostMapping("/save")
    fun saveCurrentWeather(
        @NotNull @RequestParam lat: Double,
        @NotNull @RequestParam lon: Double
    ): Mono<CurrentWeather>

    @PostMapping("/bulk/save")
    fun saveCurrentWeather(): Flux<CurrentWeather>
}