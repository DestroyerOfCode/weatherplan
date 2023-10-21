package com.babkovic.openweather.service

import com.babkovic.current.exception.CurrentWeatherException
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.openweather.config.OpenWeatherClientService
import com.babkovic.openweather.model.domain.City
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

@Service
class OpenWeatherServiceImpl(
    @Autowired private val client: OpenWeatherClientService,
    @Autowired private val objectMapper: ObjectMapper
) : OpenWeatherService {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(OpenWeatherServiceImpl::class.java)
    }
    override fun getCurrentWeatherFromOpenWeatherByCityIds(): Flux<CurrentWeather> {
        val cities: List<City> = getCityCoords()
        LOGGER.info("Starting communication with openweatherapi.org")

        return cities.toFlux()
            .log("Sending the request to openweatherapi.org")
            .flatMap(handleCurrentWeatherCall)
            .log("Received the current object from openweatherapi.org")
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
        { e: Throwable -> throw CurrentWeatherException("An error occurred fetching weather", e) }

    private fun getCityCoords(): List<City> {
        return objectMapper.readValue(
            Thread.currentThread().getContextClassLoader().getResourceAsStream("SlovakCities.json"),
            objectMapper.typeFactory.constructCollectionType(List::class.java, City::class.java)
        )
    }
}