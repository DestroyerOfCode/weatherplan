package com.babkovic

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.babkovic"]/*,
    exclude = [WebMvcAutoConfiguration::class]*/
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}