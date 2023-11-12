package com.babkovic.gateway
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.cloud.CloudPlatform
@SpringBootApplication(scanBasePackages = ["com.babkovic.gateway"])
class Application {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplication(Application::class.java)
            app.run(*args)
        }
    }
}