package com.babkovic.current.config

import com.babkovic.current.model.domain.CurrentWeather
import org.jetbrains.annotations.NotNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@HttpExchange(contentType = MediaType.APPLICATION_NDJSON_VALUE)
@ResponseBody
interface CurrentWeatherClientService {


    @GetExchange("/test")
    fun test(): ResponseEntity<String>

    @GetExchange
    fun currentWeathers(@NotNull @RequestParam lat: Double, @NotNull @RequestParam lon: Double): Mono<CurrentWeather>

    @GetExchange("/all")
    fun currentWeathers(): Flux<CurrentWeather>

}
