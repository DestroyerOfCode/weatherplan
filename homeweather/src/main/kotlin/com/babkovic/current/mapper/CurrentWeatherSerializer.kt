package com.babkovic.current.mapper

import com.babkovic.current.model.domain.CurrentWeather
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.stereotype.Component

@Component
class CurrentWeatherSerializer : JsonSerializer<CurrentWeather>() {
    override fun serialize(value: CurrentWeather, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        gen.writeStringField("_id", value._id.toString())
        gen.writeNumberField("lat", value.lat)
        gen.writeNumberField("lon", value.lon)
        gen.writeStringField("timezone", value.timezone)
        gen.writeObjectField("current", value.current)
        gen.writeEndObject()
    }
}
