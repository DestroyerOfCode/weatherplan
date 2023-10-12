package com.babkovic.openweather.exception


class OpenWeatherApiException : RuntimeException {
    constructor(message: String?, throwable: Throwable) : super(message, throwable)
    constructor(message: String?) : super(message)
}