package com.babkovic

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan("com.babkovic")
class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Application>(*args)
//    for (bean: String in ctx.beanDefinitionNames) {
//        println(bean)
//    }

        }
    }

}