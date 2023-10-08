package com.babkovic.current.config

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Mono


@HttpExchange(contentType = MediaType.APPLICATION_NDJSON_VALUE)
interface CurrentWeatherClientService {

//    @ResponseBody
    @GetExchange("/getByCoords")
    fun currentWeathers(@RequestParam lat: Double, @RequestParam lon: Double): Mono<CurrentWeather>
}
