package com.babkovic.current.exception

class CurrentWeatherException : RuntimeException {
    constructor(message: String?, throwable: Throwable) : super(message, throwable)
    constructor(message: String?) : super(message)
}