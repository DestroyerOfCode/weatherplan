package com.babkovic.smssender.config

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue

@ConfigurationProperties("twilio")
class TwilioProperties(
    @DefaultValue val twilio: Twilio,
) {
    data class Twilio(
        @NotNull val accountSid: String,
        @NotNull val authToken: String,
        @NotNull val trialNumber: String,
    )
}