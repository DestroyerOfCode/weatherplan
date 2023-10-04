package com.babkovic.openweather.exception.handler

import com.babkovic.openweather.controller.OpenWeatherControllerImpl
import com.babkovic.openweather.exception.OpenWeatherApiException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@ControllerAdvice(basePackageClasses = [OpenWeatherControllerImpl::class])
class OpenWeatherApiExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseBody
    @ExceptionHandler(OpenWeatherApiException::class)
    fun handleOpenWeatherApiException(
        request: ServerWebExchange, ex: OpenWeatherApiException
    ): Mono<*> {
        return super.handleExceptionInternal(
            ex,
            ex.message,
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        )
    }
}