package com.babkovic.current.config

import com.babkovic.current.mapper.CurrentWeatherDeserializer
import com.babkovic.current.model.domain.CurrentWeather
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CurrentWeatherJacksonConfig {

    @Bean
    fun currentWeatherObjectMapper(@Qualifier("objectMapper") initialObjectMapper: ObjectMapper): ObjectMapper {
        registerCurrentWeather(initialObjectMapper)

        return initialObjectMapper
    }

    private fun registerCurrentWeather(objectMapper: ObjectMapper) {
        val module = SimpleModule()
        module.addDeserializer(CurrentWeather::class.java, CurrentWeatherDeserializer())

        objectMapper.registerModule(module)
    }
}