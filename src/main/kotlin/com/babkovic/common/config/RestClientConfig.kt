package com.babkovic.common.config

import com.babkovic.common.config.Constants.Companion.APP_ID
import com.babkovic.common.config.Constants.Companion.OPEN_WEATHER_URL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class RestClientConfig {

    @Bean
    fun restClientBuilder(): RestClient.Builder {
        return RestClient.builder()
    }

    @Bean(name = ["openWeatherRestClient"])
    fun openWeatherClient(restClientBuilder: RestClient.Builder): RestClient {
        val componentsBuilder = UriComponentsBuilder.fromHttpUrl(OPEN_WEATHER_URL)
            .queryParam(APP_ID, System.getenv("appid"))

        val restClient = restClientBuilder.uriBuilderFactory(
            DefaultUriBuilderFactory(componentsBuilder)
        )
            .build()

        return restClient
    }

}