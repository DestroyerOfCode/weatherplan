package com.babkovic.config

import com.babkovic.config.Constants.Companion.APP_ID
import com.babkovic.config.Constants.Companion.OPEN_WEATHER_URL
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

    @Bean(name = ["currentWeatherRestClient"])
    fun currentWeatherClient(restClientBuilder: RestClient.Builder): RestClient {
        val componentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/open/current")

        val restClient = restClientBuilder.uriBuilderFactory(
            DefaultUriBuilderFactory(componentsBuilder)
        )
            .build()

        return restClient
    }

}