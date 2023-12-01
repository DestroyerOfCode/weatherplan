package com.babkovic.home.current.service

import com.babkovic.home.current.config.HomeWeatherClientService
import com.babkovic.home.current.exception.CurrentWeatherException
import com.babkovic.home.current.model.domain.CurrentWeather
import com.babkovic.home.current.model.repository.CurrentWeatherRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@CurrentWeatherService
class CurrentWeatherServiceImpl(
    @Autowired private val client: HomeWeatherClientService,
    @Autowired private val repository: CurrentWeatherRepository
) : ICurrentWeatherService {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CurrentWeatherServiceImpl::class.java)
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

    private val handleOpenWeatherError = { e: Throwable ->
        LOGGER.error("During the handling of weather an error happened")
        throw CurrentWeatherException("An error occurred fetching weather", e)
    }
}