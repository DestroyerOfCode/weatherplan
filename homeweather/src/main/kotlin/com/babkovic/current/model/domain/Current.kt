package com.babkovic.current.model.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Current(
    var dt: Int,
    var sunrise: Int,
    var sunset: Int,
    var temp: Double,
    @JsonProperty("feels_like")
    var feelsLike: Double,
    var pressure: Int,
    var humidity: Int,
    @JsonProperty("dew_point")
    var dewPoint: Double,
) {
    // Builder class for Current
    data class Builder(
        private var dt: Int = 0,
        private var sunrise: Int = 0,
        private var sunset: Int = 0,
        private var temp: Double = 0.0,
        private var feelsLike: Double = 0.0,
        private var pressure: Int = 0,
        private var humidity: Int = 0,
        private var dewPoint: Double = 0.0
    ) {
        fun dt(dt: Int) = apply { this.dt = dt }
        fun sunrise(sunrise: Int) = apply { this.sunrise = sunrise }
        fun sunset(sunset: Int) = apply { this.sunset = sunset }
        fun temp(temp: Double) = apply { this.temp = temp }
        fun feelsLike(feelsLike: Double) = apply { this.feelsLike = feelsLike }
        fun pressure(pressure: Int) = apply { this.pressure = pressure }
        fun humidity(humidity: Int) = apply { this.humidity = humidity }
        fun dewPoint(dewPoint: Double) = apply { this.dewPoint = dewPoint }

        fun build() = Current(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            temp = temp,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint
        )
    }
}
