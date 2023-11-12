package com.babkovic.home.current.config

import com.babkovic.home.current.mapper.CurrentWeatherDeserializer
import com.babkovic.home.current.mapper.CurrentWeatherSerializer
import com.babkovic.home.current.model.domain.CurrentWeather
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class CurrentWeatherJacksonConfig {
    @Bean
    fun openWeatherObjectMapper(@Qualifier("objectMapper") initialObjectMapper: ObjectMapper): ObjectMapper {
        val objectMapper = ObjectMapper()
        registerCurrentWeather(objectMapper)

        return objectMapper
    }

    private fun registerCurrentWeather(objectMapper: ObjectMapper) {
        val module = SimpleModule()
        module.addDeserializer(CurrentWeather::class.java, CurrentWeatherDeserializer())
        module.addSerializer(CurrentWeather::class.java, CurrentWeatherSerializer())
        objectMapper.registerModule(module)
    }
}