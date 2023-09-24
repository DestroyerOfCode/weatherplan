package com.babkovic.common.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckControllerImplIT(
    @Autowired val restTemplate: TestRestTemplate
) {

    @Test
    fun `When calling Healthcheck Endpoint, should return Response Entity ok`() {
        //given and when
        val entity: ResponseEntity<String> = restTemplate.getForEntity<String>("/health")

        //then
        assertEquals(HttpStatus.OK, entity.statusCode, "Health check is not working!\n")
        assertEquals("I am okay\n", entity.body, "Health check body is not right!\n")

    }
}