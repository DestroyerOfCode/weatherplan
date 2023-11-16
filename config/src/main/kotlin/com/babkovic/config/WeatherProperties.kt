package com.babkovic.config

import com.babkovic.config.Constants.Companion.OPEN_WEATHER_API_URL
import com.babkovic.config.Constants.Companion.DEFAULT_WEATHER_PLAN_HOME_URL
import com.babkovic.config.Constants.Companion.DEFAULT_WEATHER_PLAN_OPEN_URL
import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue


@ConfigurationProperties("weather")
class WeatherProperties(
    @DefaultValue val gateway: ServerProps,
    @param:DefaultValue(OPEN_WEATHER_API_URL) val openWeatherApiUrl: String,
    @param:DefaultValue(DEFAULT_WEATHER_PLAN_OPEN_URL) val weatherPlanOpenUrl: String,
    @param:DefaultValue(DEFAULT_WEATHER_PLAN_HOME_URL) val weatherPlanHomeUrl: String,
) {
    data class ServerProps(
        @NotNull @param:DefaultValue("http") val schema: String,
        @NotNull @param:DefaultValue("localhost") val address: String,
        @NotNull @param:DefaultValue("8083") val port: String
    )

    fun createServerUri(schema: String, address: String, port: String): String {
        return "$schema://$address:$port"
    }
}