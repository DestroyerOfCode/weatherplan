package com.babkovic.current.controller

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Flux

@RequestMapping("/current")
interface WeatherCurrentController {

    @GetMapping
    fun fetchCurrentWeather(): Flux<CurrentWeather>
}