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

@Configuration(proxyBeanMethods = false)
class WebClientConfig(private val properties: WeatherProperties) {
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean(name = ["currentWeatherWebClient"])
    fun webClient(builder: WebClient.Builder): WebClient {
        val componentsBuilder = UriComponentsBuilder.fromHttpUrl(properties.weatherPlanOpenUrl)

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

    @Bean(name = ["twilioWebClient"])
    fun twilioWebClient(webClientBuilder: WebClient.Builder): WebClient {
        val componentsBuilder = UriComponentsBuilder.fromHttpUrl(properties.openWeatherApiUrl)
            .queryParam(Constants.APP_ID, System.getenv("appid"))
        val webClient = webClientBuilder.uriBuilderFactory(
            DefaultUriBuilderFactory(componentsBuilder)
        )
            .build()

        return webClient
    }
}