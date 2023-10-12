package com.babkovic.current.service

import com.babkovic.current.config.CurrentWeatherClientService
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.model.repository.CurrentWeatherRepository
import com.babkovic.openweather.exception.OpenWeatherApiException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class WeatherCurrentServiceImpl(
    @Autowired private val client: CurrentWeatherClientService,
    @Autowired @Qualifier("currentWeatherRestClient") private val restClient: RestClient,
    @Autowired private val repository: CurrentWeatherRepository
) : WeatherCurrentService {
    override fun fetchCurrentWeather(): Flux<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun fetchCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather> {
        val ret = client.currentWeathers(lat, lon)
        ret.subscribe()
        return ret
    }

    override fun saveCurrentWeather(lat: Double, lon: Double) {
//        client.currentWeathers(lat, lon)
//            .onErrorMap(handleOpenWeatherError)
//            .subscribe { x ->
//                val x1 = x.copy()
//                repository.save(x1)
//            }
        restClient.get().uri("http://localhost:8080/open/current/getByCoords?lat=$lat&lon=$lon")
            .accept(MediaType.APPLICATION_NDJSON)
            .retrieve()
            .body(Mono::class.java)
    }


    private val handleOpenWeatherError =
        { e: Throwable -> throw OpenWeatherApiException("An error occurred fetching weather", e) }

}