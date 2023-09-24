package com.babkovic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val ctx: ConfigurableApplicationContext = runApplication<Application>(*args)
    for (bean: String in ctx.beanDefinitionNames) {
        println(bean)
    }
}