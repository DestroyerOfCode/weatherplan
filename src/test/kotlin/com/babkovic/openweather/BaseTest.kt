package com.babkovic.openweather

import com.babkovic.Application
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Application::class])
class BaseTest {

    companion object {
        @JvmStatic
        protected val BASE_URL: String = "http://localhost:8080/"
    }

    @Autowired
    private lateinit var ctx: ApplicationContext

    protected lateinit var client: WebTestClient

    @BeforeAll
    fun initApplication() {
        client = WebTestClient.bindToApplicationContext(ctx).build()
    }


}