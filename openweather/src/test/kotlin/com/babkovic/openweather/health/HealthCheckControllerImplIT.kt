package com.babkovic.openweather.health

import com.babkovic.openweather.BaseTest
import com.babkovic.openweather.controller.OpenWeatherControllerIT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HealthCheckControllerImplIT : BaseTest() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(OpenWeatherControllerIT::class.java)
    }

    @Test
    fun `when calling Healthcheck Endpoint, should return Response Entity ok`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        client.get().uri("${BASE_URL}/api/health").exchange().expectStatus().isOk
            .expectBody(String::class.java)
            .isEqualTo("I am okay\n")
            .returnResult()

        LOGGER.info("Ending test ${testInfo.displayName}\n")

    }
}