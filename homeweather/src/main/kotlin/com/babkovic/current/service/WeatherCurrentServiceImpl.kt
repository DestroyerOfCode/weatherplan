package com.babkovic.current.service

import com.babkovic.current.config.CurrentWeatherClientService
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.model.repository.CurrentWeatherRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class WeatherCurrentServiceImpl(
    @Autowired private val client: CurrentWeatherClientService,
    @Autowired private val client2: RestClient.Builder,
    @Autowired private val repository: CurrentWeatherRepository
) : WeatherCurrentService {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(WeatherCurrentServiceImpl::class.java)
    }

    override fun fetchCurrentWeather(): Flux<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun fetchCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather> {
        val ret = client.currentWeathers(lat, lon)
        return ret
    }

    @Transactional
    override fun saveCurrentWeather(): Flux<CurrentWeather> {
        LOGGER.info("Going to call openCurrentWeather service")
        return client.currentWeathers()
            .log()
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { x ->
                LOGGER.info("Going to save $x to db")
                repository.save(x).subscribe()
                LOGGER.info("Save $x to db")
            }
            .doOnError(handleOpenWeatherError)
            .doOnTerminate {
                LOGGER.info("Going to leave method saveCurrentWeather")
            }
    }


    @Transactional
    override fun saveCurrentWeather(lat: Double, lon: Double): Mono<CurrentWeather> {
        LOGGER.info("Going to call openCurrentWeather service")
        return client.currentWeathers(lat, lon)
            .log()
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { x ->
                LOGGER.info("Going to save $x to db")
                repository.save(x).subscribe()
                LOGGER.info("Save $x to db")
            }
            .doOnError(handleOpenWeatherError)
            .doOnTerminate {
                LOGGER.info("Going to leave method saveCurrentWeather")
            }
    }

    override fun test(): ResponseEntity<String> {
        return client2.build().get().uri("http://localhost:8080/open/current/test").retrieve().toEntity(String::class.java)

    }


    private val handleOpenWeatherError =
        { e: Throwable ->
            LOGGER.error("During the handling of weather an error happened")
//            throw OpenWeatherApiException("An error occurred fetching weather", e)
        }

}