package com.babkovic.openweather.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration(proxyBeanMethods = false)
class OpenWeatherRestClientProxy {
    @Bean
    fun openWeatherClient(@Qualifier("openWeatherRestClient") restClient: RestClient): OpenWeatherClientService {
        val factory: HttpServiceProxyFactory =
            HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(restClient)
            )
                .build()

        return factory.createClient(OpenWeatherClientService::class.java)
    }
}