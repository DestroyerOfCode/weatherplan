package com.babkovic.smssender;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(
    scanBasePackages = [
        "com.babkovic.smssender",
        "com.babkovic.config",
        "com.babkovic.home.current.model.domain",
    ]
)
@EnableReactiveMongoRepositories(
    basePackages = [
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