package com.babkovic.smssender.service

import com.babkovic.home.current.model.domain.CurrentWeather
import com.babkovic.smssender.config.TwilioClientService
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
    @Autowired private val webClient: TwilioClientService
) : ITwilioService {


    override fun sendCurrentWeatherSms() {
        userRepository.findAll()
            .flatMap { user ->
                webClient.currentWeathers(user.coord.lat, user.coord.lon)
                    .publishOn(Schedulers.boundedElastic())
                    .doOnNext { weather -> sendCurrentWeatherSms(user, weather).subscribe() }
            }
            .subscribe()
    }

    override fun sendCurrentWeatherSms(user: User, weather: CurrentWeather): Mono<Message> {
        val phoneNumberFrom = PhoneNumber(twilioProperties.twilio.trialNumber)

        val creator: MessageCreator =
            Message.creator(PhoneNumber(user.phoneNumber), phoneNumberFrom, "" + (weather.current.feelsLike))

        //this is because I have only trial account and dont want to spend money
//        val message = creator.create()
//        return Mono.just(message)
        return Mono.empty()
    }
}