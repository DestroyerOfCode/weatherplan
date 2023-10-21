package com.babkovic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(scanBasePackages = ["com.babkovic"])
@EnableReactiveMongoRepositories(basePackages = ["com.babkovic"])
class Application {

    /*
    * https://docs.spring.io/spring-boot/docs/current/reference/html/web.html
      Adding both spring-boot-starter-web and spring-boot-starter-webflux modules in your application results in
    * Spring Boot auto-configuring Spring MVC, not WebFlux. This behavior has been chosen because many Spring developers
    * add spring-boot-starter-webflux to their Spring MVC application to use the reactive WebClient. You can still enforce
    * your choice by setting the chosen application type to */
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplication(Application::class.java)
            app.run(*args)
        }
    }
}