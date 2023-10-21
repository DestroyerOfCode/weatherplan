package com.babkovic.current.exception.handler

import com.babkovic.current.controller.CurrentWeatherControllerImpl
import com.babkovic.current.exception.CurrentWeatherException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@ControllerAdvice(basePackageClasses = [CurrentWeatherControllerImpl::class])
class CurrentWeatherApiExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseBody
    @ExceptionHandler(CurrentWeatherException::class)
    fun handleOpenWeatherApiException(
        request: ServerWebExchange, ex: CurrentWeatherException
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