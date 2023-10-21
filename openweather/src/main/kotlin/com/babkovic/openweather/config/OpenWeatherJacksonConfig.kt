package com.babkovic.openweather.config

import com.babkovic.openweather.mapper.CityDeserializer
import com.babkovic.openweather.model.domain.City
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class OpenWeatherJacksonConfig {

    @Bean
    fun currentWeatherObjectMapper(@Qualifier("objectMapper") initialObjectMapper: ObjectMapper): ObjectMapper {
        registerCity(initialObjectMapper)

        return initialObjectMapper
    }

    private fun registerCity(objectMapper: ObjectMapper) {
        val module = SimpleModule()
        module.addDeserializer(City::class.java, CityDeserializer())

        objectMapper.registerModule(module)
    }
}