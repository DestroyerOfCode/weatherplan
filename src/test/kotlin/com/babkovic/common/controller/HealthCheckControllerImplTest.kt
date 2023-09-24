package com.babkovic.common.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class HealthCheckControllerImplTest {

    private lateinit var controllerImpl: HealthCheckController

    @BeforeEach
    fun setUp() {
        controllerImpl = HealthCheckControllerImpl()
    }

    @Test
    fun whenCallingMethodHealthCheckShouldReturnString() {
        //given and when
        val returnString: ResponseEntity<String> = controllerImpl.checkHealth()

        //then
        assertEquals("I am okay\n", returnString.body)
    }
}