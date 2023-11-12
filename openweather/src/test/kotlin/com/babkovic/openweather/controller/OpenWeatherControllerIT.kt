package com.babkovic.openweather.controller

import com.babkovic.home.current.model.domain.CurrentWeather
import com.babkovic.openweather.BaseTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.FluxExchangeResult
import reactor.kotlin.test.test
import reactor.test.StepVerifier
import kotlin.test.assertEquals

class OpenWeatherControllerIT : BaseTest() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(OpenWeatherControllerIT::class.java)
    }

    @Test
    fun `should return current, when calling get current weather from open weather by coords`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        //given and when
        val url = "$GATEWAY_URL/open/current?lat=49.136372&lon=20.24386"
        val resultFlux: FluxExchangeResult<CurrentWeather> = callCurrentWeatherFlux(url)

        //then
        StepVerifier.create(resultFlux.responseBody)
            .assertNext { currentWeather ->
                run {
                    assertEquals(20.2439, currentWeather.lat, "latitude is not the same")
                    assertEquals(49.1364, currentWeather.lon, "longitude is not the same")
                    assertEquals("Asia/Riyadh", currentWeather.timezone, "timezone is not the same")
                }
            }

            .thenCancel()
            .verify()

        LOGGER.info("Ending test ${testInfo.displayName}\n")

    }

    @Test
    fun `should return current flux, when calling get current weather from open weather`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        //given and when
        val url = "$GATEWAY_URL/open/current/all"
        val resFlux: FluxExchangeResult<CurrentWeather> = callCurrentWeatherFlux(url)

        //then
        resFlux.responseBody.test()
            .assertNext { currentWeather ->
                run {
                    assertEquals(20.2624, currentWeather.lat, "latitude is not the same")
                    assertEquals(49.271, currentWeather.lon, "longitude is not the same")
                    assertEquals("Asia/Riyadh", currentWeather.timezone, "timezone is not the same")
                }
            }
            .assertNext { currentWeather ->
                run {
                    assertEquals(20.2439, currentWeather.lat, "latitude is not the same")
                    assertEquals(49.1364, currentWeather.lon, "longitude is not the same")
                    assertEquals("Asia/Riyadh", currentWeather.timezone, "timezone is not the same")
                }
            }
            .thenCancel()
            .verify()

        LOGGER.info("Ending test ${testInfo.displayName}\n")

    }

    private fun callCurrentWeatherFlux(urlToCall: String) = client.get()
        .uri(urlToCall)
        .accept(MediaType.APPLICATION_NDJSON)
        .exchange()
        .expectStatus().isOk
        .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
        .returnResult(CurrentWeather::class.java)
}