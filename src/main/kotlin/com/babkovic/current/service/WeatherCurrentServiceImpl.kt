package com.babkovic.current.service

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class WeatherCurrentServiceImpl : WeatherCurrentService {
    override fun fetchCurrentWeather(): Flux<CurrentWeather> {
        TODO("Not yet implemented")
    }
}