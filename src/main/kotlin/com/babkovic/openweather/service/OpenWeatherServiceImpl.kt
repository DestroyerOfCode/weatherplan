package com.babkovic.openweather.service

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.openweather.config.OpenWeatherClientService
import com.babkovic.openweather.exception.OpenWeatherApiException
import com.babkovic.openweather.model.domain.City
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.io.File

@Service
class OpenWeatherServiceImpl(
    @Autowired private val client: OpenWeatherClientService,
    @Autowired private val objectMapper: ObjectMapper
) : OpenWeatherService {
    override fun getCurrentWeatherFromOpenWeatherByCityIds(): Flux<CurrentWeather> {
        val cities: List<City> = getCityCoords()
        return cities.toFlux()
            .flatMap(handleCurrentWeatherCall)
            .onErrorMap(handleOpenWeatherError)


    }

    override fun getCurrentWeatherFromOpenWeatherByCityId(lat: Double, lon: Double): Mono<CurrentWeather> {
        return client.currentWeathers(lon, lat).toMono()
            .onErrorMap(handleOpenWeatherError)
    }

    private val handleCurrentWeatherCall = { city: City ->
        Mono.just(client.currentWeathers(city.coord.lon, city.coord.lat))
            .onErrorMap(handleOpenWeatherError)
    }

    private val handleOpenWeatherError =
        { e: Throwable -> throw OpenWeatherApiException("An error occurred fetching weather", e) }

    private fun getCityCoords(): List<City> {
        return objectMapper.readValue(
            File("src/main/resources/SlovakCities.json"),
            objectMapper.typeFactory.constructCollectionType(List::class.java, City::class.java)
        )
    }
}