package com.babkovic.smssender.config

import com.babkovic.home.current.model.domain.CurrentWeather
import org.jetbrains.annotations.NotNull
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Mono


@HttpExchange(contentType = MediaType.APPLICATION_NDJSON_VALUE)
@ResponseBody
interface TwilioClientService {
    @GetExchange(accept = [MediaType.APPLICATION_NDJSON_VALUE])
    fun currentWeathers(@NotNull @RequestParam lat: Double, @NotNull @RequestParam lon: Double): Mono<CurrentWeather>


}
