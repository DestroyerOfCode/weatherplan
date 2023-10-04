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

}
