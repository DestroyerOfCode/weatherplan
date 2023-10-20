package com.babkovic.current.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class CurrentWeatherWebClientProxy {
    @Bean
    fun currentWeatherClient(@Qualifier("currentWeatherWebClient") webClient: WebClient): CurrentWeatherClientService {
        val factory: HttpServiceProxyFactory =
            HttpServiceProxyFactory.builderFor(
                WebClientAdapter
                    .create(webClient)
            )
                .build()

        return factory.createClient(CurrentWeatherClientService::class.java)
    }
}