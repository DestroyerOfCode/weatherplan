package com.babkovic.current.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class CurrentWeatherRestClientProxy {
    @Bean
    fun currentWeatherClient(@Qualifier("currentWeatherRestClient") restClient: RestClient): CurrentWeatherClientService {
        val factory: HttpServiceProxyFactory =
            HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(restClient)
            )
                .build()

        return factory.createClient(CurrentWeatherClientService::class.java)
    }
}