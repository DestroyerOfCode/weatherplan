package com.babkovic.config

import com.babkovic.config.Constants.Companion.APP_ID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriComponentsBuilder

@Configuration(proxyBeanMethods = false)
class RestClientConfig(private val properties: WeatherProperties) {
    @Bean
    fun restClientBuilder(): RestClient.Builder {
        return RestClient.builder()
    }

    @Bean(name = ["openWeatherRestClient"])
    fun openWeatherClient(restClientBuilder: RestClient.Builder): RestClient {
        println("appid:${System.getenv("appid")}")
        val componentsBuilder = UriComponentsBuilder.fromHttpUrl(properties.openWeatherApiUrl)
            .queryParam(APP_ID, System.getenv("appid"))

        val restClient = restClientBuilder.uriBuilderFactory(
            DefaultUriBuilderFactory(componentsBuilder)
        )
            .build()

        return restClient
    }
}