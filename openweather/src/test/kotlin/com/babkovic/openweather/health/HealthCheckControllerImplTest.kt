package com.babkovic.openweather.health

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class HealthCheckControllerImplTest {

    private var controllerImpl: HealthCheckController = HealthCheckControllerImpl()

    @BeforeEach
    fun setUp() {
        controllerImpl = HealthCheckControllerImpl()
    }

    @Test
    fun `when calling healthcheck should return response entity string`() {
        //given and when
        val returnString: ResponseEntity<String> = controllerImpl.checkHealth()

        //then
        assertEquals("I am okay\n", returnString.body)
    }
}