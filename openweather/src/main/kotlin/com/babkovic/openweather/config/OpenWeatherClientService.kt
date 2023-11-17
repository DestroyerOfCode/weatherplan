package com.babkovic.openweather.config

import com.babkovic.home.current.model.domain.CurrentWeather
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange


@HttpExchange(contentType = MediaType.APPLICATION_JSON_VALUE)
interface OpenWeatherClientService {

    @ResponseBody
    @GetExchange
    fun currentWeathers(@RequestParam lat: Double, @RequestParam lon: Double): CurrentWeather
}
