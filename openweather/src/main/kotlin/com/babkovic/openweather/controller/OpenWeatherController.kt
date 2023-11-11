package com.babkovic.openweather.controller

import com.babkovic.current.model.domain.CurrentWeather
import org.jetbrains.annotations.NotNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * This controller produces a non-blocking stream of data thanks to the New Delimiter JSON and Webflux
 * As the Weather instance objects get appended to the flux object, they are being sent to the client in parallel
 * The actual HTTP request lasts during the whole duration
 * This is NOT a webhook
 */
@RequestMapping("/api/open/current", produces = [MediaType.APPLICATION_NDJSON_VALUE])
@ResponseBody
interface OpenWeatherController {

    @GetMapping("/test")
    fun test(): ResponseEntity<String>

    @GetMapping("/all")
    fun getCurrentWeatherFromOpenWeather(): ResponseEntity<Flux<CurrentWeather>>

    @GetMapping
    fun getCurrentWeatherFromOpenWeatherByCoords(
        @NotNull @RequestParam lat: Double,
        @NotNull @RequestParam lon: Double
    ): ResponseEntity<Mono<CurrentWeather>>

}
