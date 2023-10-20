package com.babkovic.current.mapper

import com.babkovic.current.exception.CurrentWeatherException
import com.babkovic.current.model.domain.Current
import com.babkovic.current.model.domain.CurrentWeather
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.bson.BsonDocument
import org.bson.BsonObjectId
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.text.ParseException
import java.util.*


@Component
class CurrentWeatherDeserializer :
    JsonDeserializer<CurrentWeather>() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CurrentWeatherDeserializer::class.java)
    }

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): CurrentWeather {
        try {
            val node: JsonNode = p.codec.readTree(p)

            val _id: ObjectId =
                if (node.hasNonNull("_id")) ObjectId(node.get("_id").asText())
                else ObjectId.get()
            val lat: Double = node.get("lat").doubleValue()
            val lon: Double = node.get("lon").doubleValue()
            val timezone: String = node.get("timezone").textValue()
            val current = deserializeCurrent(node)

            return CurrentWeather(_id, lat, lon, timezone, current)
        } catch (e: ParseException) {
            LOGGER.error("An error has occurred parsing a node to CurrentWeather", e)
        } catch (e: NullPointerException) {
            LOGGER.error(
                "An error has occurred deserializing a node to CurrentWeather. One of the fields was non-existent", e
            )
        }
        throw CurrentWeatherException("An exception was thrown when deserializing Current Weather")
    }

    private fun deserializeCurrent(node: JsonNode): Current {
        val dt: Int = node.get("current").get("dt").intValue()
        val sunrise: Int = node.get("current").get("sunrise").intValue()
        val sunset: Int = node.get("current").get("sunset").intValue()
        val temp: Double = node.get("current").get("temp").doubleValue()
        val feelsLike: Double = node.get("current").get("feels_like").doubleValue()
        val pressure: Int = node.get("current").get("pressure").intValue()
        val humidity: Int = node.get("current").get("humidity").intValue()
        val dewPoint: Double = node.get("current").get("dew_point").doubleValue()
        return Current(dt, sunrise, sunset, temp, feelsLike, pressure, humidity, dewPoint)
    }

    private fun toHex(arg: String): String {
        return String.format("%x", BigInteger(1, arg.toByteArray()))
    }
}