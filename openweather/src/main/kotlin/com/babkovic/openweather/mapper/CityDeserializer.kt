package com.babkovic.openweather.mapper

import com.babkovic.home.current.exception.CurrentWeatherException
import com.babkovic.openweather.model.domain.City
import com.babkovic.openweather.model.domain.Coord
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.text.ParseException

@Component
class CityDeserializer : JsonDeserializer<City>() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CityDeserializer::class.java)
    }

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): City {
        try {
            val node: JsonNode = p.codec.readTree(p)

            val id: Long = node.get("id").longValue()
            val name: String = node.get("name").textValue()
            val state: String = node.get("state").textValue()
            val country: String = node.get("country").textValue()
            val coords = deserializeCoords(node)

            return City(id, name, state, country, coords)
        } catch (e: ParseException) {
            LOGGER.error("An error has occurred parsing a node to City", e)
        } catch (e: NullPointerException) {
            LOGGER.error(
                "An error has occurred deserializing a node to City. One of the fields was non-existent", e
            )
        }
        throw CurrentWeatherException("An exception was thrown when deserializing Current Weather")
    }

    private fun deserializeCoords(node: JsonNode): Coord {
        val lat: Double = node.get("coord").get("lat").doubleValue()
        val lon: Double = node.get("coord").get("lon").doubleValue()
        return Coord(lon, lat)
    }

}
