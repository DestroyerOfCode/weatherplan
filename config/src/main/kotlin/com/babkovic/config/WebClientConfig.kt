package com.babkovic.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriComponentsBuilder
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.time.temporal.ChronoUnit.MILLIS

@Configuration
class WebClientConfig {

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean(name = ["currentWeatherWebClient"])
    fun webClient(builder: WebClient.Builder): WebClient {
        val componentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/open/current")

        val webClient = builder.uriBuilderFactory(
            DefaultUriBuilderFactory(componentsBuilder)
        ).clientConnector(
            ReactorClientHttpConnector(
                HttpClient.create().responseTimeout(Duration.of(60000L, MILLIS))
            )
        )
            .build()

        return webClient
    }
}