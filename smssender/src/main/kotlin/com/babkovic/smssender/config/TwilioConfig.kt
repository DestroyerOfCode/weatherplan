package com.babkovic.smssender.config

import com.twilio.Twilio
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TwilioProperties::class)
class TwilioConfig {


    @Bean
    fun twilioProperties(twilioProperties: TwilioProperties): TwilioProperties {
        Twilio.init(
            twilioProperties.twilio.accountSid,
            twilioProperties.twilio.authToken,
        )
        return twilioProperties
    }
}