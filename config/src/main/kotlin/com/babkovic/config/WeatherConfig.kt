package com.babkovic.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("weather")
class WeatherConfig {
    data class Weather(
        private val schema: String,
        private val subDomain: String,
        private val port: String
    )
}