package com.babkovic.gateway

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["com.babkovic.gateway"])
class Application {


    companion object {

        private val LOGGER: Logger = LoggerFactory.getLogger(Application::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplication(Application::class.java)
            LOGGER.info("OPENWEATHER_ROUTE_URI: " + System.getenv("OPENWEATHER_ROUTE_URI"))
            LOGGER.info("SMSSENDER_ROUTE_URI: " + System.getenv("SMSSENDER_ROUTE_URI"))
            LOGGER.info("HOMEWEATHER_ROUTE_URI: " + System.getenv("HOMEWEATHER_ROUTE_URI"))
            app.run(*args)
        }
    }
}