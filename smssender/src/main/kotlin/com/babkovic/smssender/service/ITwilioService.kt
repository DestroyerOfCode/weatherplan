package com.babkovic.smssender.service

import com.babkovic.home.current.model.domain.CurrentWeather
import com.babkovic.smssender.model.domain.User
import com.twilio.rest.api.v2010.account.Message
import reactor.core.publisher.Mono

interface ITwilioService {
    fun sendCurrentWeatherSms(): Unit
    fun sendCurrentWeatherSms(user: User, weather: CurrentWeather): Mono<Message>
}