package com.babkovic.smssender.config

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue

@ConfigurationProperties("messenger")
class MessengerProperties(
    @NotNull @param:DefaultValue("http") val schema: String,
    @NotNull @param:DefaultValue("localhost") val subDomain: String,
) {

}