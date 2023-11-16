package com.babkovic.smssender

import com.babkovic.config.WeatherProperties
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.time.Duration
import java.time.temporal.ChronoUnit.MINUTES


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    properties = ["spring.config.location=classpath:application-test.yml"],
    locations = ["classpath:.env"]
)
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

    @Autowired
    private lateinit var weatherProperties: WeatherProperties

    @Value("\${server.tomcat.remoteip.protocol-header-https-value}")
    private lateinit var serverSchema: String

    @Value("\${server.address}")
    private lateinit var serverAddress: String

    @LocalServerPort
    private lateinit var serverPort: String

    protected lateinit var client: WebTestClient

    @BeforeAll
    fun initApplication() {
        LOGGER.info(
            """Starting web client:
            | Schema: $serverSchema
            | Address: $serverAddress
            | Server port: $serverPort
            """.trimMargin()
        )
        BASE_URL = createUrl(serverSchema, serverPort, serverAddress)
        LOGGER.info("weatherProperties.gateway.schema: ${weatherProperties.gateway.schema}")
        LOGGER.info("weatherProperties.gateway.port: ${weatherProperties.gateway.port}")
        LOGGER.info("weatherProperties.gateway.address: ${weatherProperties.gateway.address}")
        GATEWAY_URL = createUrl(
            weatherProperties.gateway.schema,
            weatherProperties.gateway.port,
            weatherProperties.gateway.address
        )
        client = WebTestClient.bindToServer().baseUrl(BASE_URL)
            .responseTimeout(Duration.of(1, MINUTES)).build()
    }

    private fun createUrl(schema: String, port: String, address: String): String {
        return "$schema://${address}:${port}"
    }


}