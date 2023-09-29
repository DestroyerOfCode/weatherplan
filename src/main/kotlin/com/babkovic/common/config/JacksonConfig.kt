package com.babkovic.common.config

import com.babkovic.current.mapper.CurrentWeatherDeserializer
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.openweather.mapper.CityDeserializer
import com.babkovic.openweather.model.domain.City
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()

        registerCurrentWeather(objectMapper)
        registerCity(objectMapper)

        return objectMapper
    }

    private fun registerCurrentWeather(objectMapper: ObjectMapper) {
        val module = SimpleModule()
        module.addDeserializer(CurrentWeather::class.java, CurrentWeatherDeserializer())

        objectMapper.registerModule(module)
    }

    private fun registerCity(objectMapper: ObjectMapper) {
        val module = SimpleModule()
        module.addDeserializer(City::class.java, CityDeserializer())

        objectMapper.registerModule(module)
    }
}