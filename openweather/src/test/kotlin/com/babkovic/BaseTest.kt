package com.babkovic

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration
import java.time.temporal.ChronoUnit.MINUTES


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class BaseTest {

    //    @LocalServerPort
//    protected  var randomServerPort =8080
//
//    protected  var  BASE_URL: String = "http://localhost:$randomServerPort/"
    companion object {
        @JvmStatic
        protected var serverPort = 8080

        @JvmStatic
        protected var BASE_URL: String = "http://localhost:$serverPort/"
    }

    @Autowired
    private lateinit var ctx: ApplicationContext

    protected lateinit var client: WebTestClient

    @BeforeAll
    fun initApplication() {
        client = WebTestClient.bindToServer().baseUrl(BASE_URL)
            .responseTimeout(Duration.of(1, MINUTES)).build()
    }


}