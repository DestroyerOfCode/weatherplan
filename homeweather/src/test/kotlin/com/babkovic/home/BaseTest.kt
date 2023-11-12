package com.babkovic.home

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.time.Duration
import java.time.temporal.ChronoUnit.MINUTES


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Testcontainers
class BaseTest {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(BaseTest::class.java)

        @JvmStatic
        protected lateinit var BASE_URL: String

        @JvmStatic
        protected lateinit var GATEWAY_URL: String

        @ServiceConnection
        var mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:7"))
    }

    /**
     * If you try to start the container with @Container, it won`t start, hence taking the lifecycle
     * to your own hands solves the issue.
     * Moreover, if you try to start web environment on random port, the client won`t load correctly
     */
    init {
        mongoDBContainer.start()
    }

    @Value("\${gateway.port}")
    private lateinit var gatewayPort: String

    @Value("\${server.port}")
    private lateinit var serverPort: String

    @Value("\${weather.home-weather.sub-domain}")
    private lateinit var subDomain: String

    @Value("\${weather.home-weather.schema}")
    private lateinit var schema: String

    protected lateinit var client: WebTestClient

    @BeforeAll
    fun initApplication() {
        LOGGER.info(
            """Starting web client:
            |schema: $schema
            |sub-domain: $subDomain
            |server port: $serverPort
            """.trimMargin()
        )
        BASE_URL = createBaseURL(serverPort)
        GATEWAY_URL = createBaseURL(gatewayPort)
        client = WebTestClient.bindToServer().baseUrl(BASE_URL)
            .responseTimeout(Duration.of(1, MINUTES)).build()
    }

    private fun createBaseURL(port: String): String {
        return "$schema://$subDomain:$port"
    }


}