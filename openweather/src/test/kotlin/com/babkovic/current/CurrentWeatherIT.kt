package com.babkovic.current

import com.babkovic.BaseTest
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.model.repository.CurrentWeatherRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import reactor.kotlin.test.test
import kotlin.test.assertEquals

class CurrentWeatherIT : BaseTest() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CurrentWeatherIT::class.java)
    }

    @AfterEach
    fun cleanUp() {
        repo.deleteAll().block()
    }

    @Autowired
    private lateinit var repo: CurrentWeatherRepository

    @Test
    fun `should return and persist current weather mono when calling save endpoint`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        //given and when
        val resFlux = client.post()
            .uri("current/save?lat=49.136372&lon=20.24386")
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
            .returnResult(CurrentWeather::class.java)

        //then
        resFlux.responseBody.test()
            .assertNext { currentWeather ->
                run {
                    assertEquals(20.2439, currentWeather.lat, "latitude is not the same")
                    assertEquals(49.1364, currentWeather.lon, "longitude is not the same")
                    assertEquals("Asia/Riyadh", currentWeather.timezone, "timezone is not the same")
                }
            }
            .thenCancel()
            .verify()
        Assertions.assertEquals(1, repo.findAll().count().block())

        LOGGER.info("Ending test ${testInfo.displayName}\n")

    }

    @Test
    fun `should save and remove entity from db when calling current weather by coords`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        //given and when
        val resFlux = client.post()
            .uri("current/bulk/save")
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
            .returnResult(CurrentWeather::class.java)

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
        Assertions.assertEquals(2, repo.findAll().count().block())

        LOGGER.info("Ending test ${testInfo.displayName}\n")

    }
}