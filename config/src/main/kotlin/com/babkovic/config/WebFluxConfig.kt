package com.babkovic.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@EnableWebFlux
@Configuration(proxyBeanMethods = false)
class WebFluxConfig : WebFluxConfigurer