package com.babkovic.current

import com.babkovic.Application
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.model.repository.CurrentWeatherRepository
import com.babkovic.BaseTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.test.context.support.AnnotationConfigContextLoader
import reactor.core.publisher.Mono
import reactor.kotlin.test.test
import java.util.stream.Stream
import kotlin.test.assertEquals

class CurrentWeatherIT : BaseTest() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CurrentWeatherIT::class.java)
    }

    @Autowired
    private lateinit var repo: CurrentWeatherRepository

    @Autowired
    private lateinit var appCtx: ApplicationContext

    @Test
    fun test(testInfo: TestInfo) {
//        Thread.sleep(10000)
        val res = client.get()
            .uri("current/test")
            .exchange()
            .expectBody(String::class.java)
            .isEqualTo("test")
            .returnResult()
        LOGGER.info(res.toString())
        LOGGER.info("Ending test ${testInfo.displayName}\n")
    }

    @Test
    fun `should return and persist current weather mono when calling save endpoint`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        val resFlux = client.post()
            .uri("current/save?lat=49.136372&lon=20.24386")
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
            .returnResult(CurrentWeather::class.java)

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

        LOGGER.info("Ending test ${testInfo.displayName}\n")

    }

    @Test
    fun `should save and remove entity from db when calling current weather by coords`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        val resFlux = client.post()
            .uri("current/bulk/save")
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
            .returnResult(CurrentWeather::class.java)

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
}