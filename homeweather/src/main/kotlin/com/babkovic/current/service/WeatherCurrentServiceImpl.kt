package com.babkovic.current.service

import com.babkovic.current.config.CurrentWeatherClientService
import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class WeatherCurrentServiceImpl(
    @Autowired private val client: CurrentWeatherClientService,

    ) : WeatherCurrentService {
    override fun fetchCurrentWeather(): Flux<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun fetchCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather> {
        return client.currentWeathers(lat, lon)
    }
}