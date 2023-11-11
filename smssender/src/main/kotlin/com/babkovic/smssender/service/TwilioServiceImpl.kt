package com.babkovic.smssender.service

import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.openweather.controller.OpenWeatherController
import com.babkovic.smssender.config.TwilioProperties
import com.babkovic.smssender.model.domain.User
import com.babkovic.smssender.model.repository.UserRepository
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@TwilioService
class TwilioServiceImpl(
    @Autowired private val twilioProperties: TwilioProperties,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val openWeatherController: OpenWeatherController
) : ITwilioService {


    override fun sendCurrentWeatherSms() {
        userRepository.findAll()
            .flatMap { user ->
                openWeatherController.getCurrentWeatherFromOpenWeatherByCoords(user.coord.lat, user.coord.lon)
                    .body
                    ?.publishOn(Schedulers.boundedElastic())
                    ?.doOnNext { weather -> sendCurrentWeatherSms(user, weather).subscribe() }
                    ?: Mono.empty() // If null or empty response, return empty Mono
            }
            .subscribe()
    }

    override fun sendCurrentWeatherSms(user: User, weather: CurrentWeather): Mono<Message> {
        val phoneNumberFrom = PhoneNumber(twilioProperties.twilio.trialNumber)

        val creator: MessageCreator =
            Message.creator(PhoneNumber(user.phoneNumber), phoneNumberFrom, "" + (weather.current.feelsLike))

        return Mono.just(creator.create())
    }
}