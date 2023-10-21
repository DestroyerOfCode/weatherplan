package com.babkovic

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration
import java.time.temporal.ChronoUnit.MINUTES


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = ["classpath:application-test.yml"])
class BaseTest {

    @Value("\${server.port}")
    private lateinit var serverPort: String

    @Value("\${weather.subdomain}")
    private lateinit var subDomain: String

    @Value("\${weather.schema}")
    private lateinit var schema: String

    protected lateinit var BASE_URL: String

    protected lateinit var client: WebTestClient

    @BeforeAll
    fun initApplication() {
        BASE_URL = createBaseURL()
        client = WebTestClient.bindToServer().baseUrl(BASE_URL)
            .responseTimeout(Duration.of(1, MINUTES)).build()
    }

    private fun createBaseURL(): String {
        return "$schema://$subDomain:$serverPort/"
    }


}