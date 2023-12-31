package com.babkovic.home

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(scanBasePackages = ["com.babkovic.home.current", "com.babkovic.config"])
@EnableReactiveMongoRepositories(basePackages = ["com.babkovic.home.current.model.repository"])
@ConfigurationPropertiesScan("com.babkovic.config")
class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplication(Application::class.java)
            app.run(*args)
        }
    }
}