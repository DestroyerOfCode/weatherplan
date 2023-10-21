package com.babkovic.config

import com.babkovic.config.Constants.Companion.OPEN_WEATHER_API_URL
import com.babkovic.config.Constants.Companion.WEATHER_PLAN_OPEN_URL
import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue


@ConfigurationProperties("weather")
class WeatherProperties(
    @DefaultValue val homeWeather: HomeWeather,
    @param:DefaultValue(OPEN_WEATHER_API_URL) val openWeatherApiUrl: String,
    @param:DefaultValue(WEATHER_PLAN_OPEN_URL) val weatherPlanOpenUrl: String
) {
    data class HomeWeather(
        @NotNull @param:DefaultValue("http") val schema: String,
        @NotNull @param:DefaultValue("localhost") val subDomain: String,
        @NotNull @param:DefaultValue("8080") val port: String
    )
}