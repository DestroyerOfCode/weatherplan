package com.babkovic.smssender;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(
    scanBasePackages = [
        "com.babkovic.smssender",
        "com.babkovic.config",
        "com.babkovic.current.model.domain",
        "com.babkovic.openweather.controller",
        "com.babkovic.openweather.config",
        "com.babkovic.openweather.service",
    ]
)
@EnableReactiveMongoRepositories(
    basePackages = [
        "com.babkovic.current.model.repository",
        "com.babkovic.smssender.model.repository"
    ]
)

class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplication(Application::class.java)
            app.run(*args)
        }
    }
}