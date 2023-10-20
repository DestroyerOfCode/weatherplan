package com.babkovic.current.model.domain

import com.babkovic.current.mapper.CurrentWeatherDeserializer
import com.babkovic.current.mapper.CurrentWeatherSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@JsonDeserialize(using = CurrentWeatherDeserializer::class, `as` = CurrentWeather::class)
@JsonSerialize(using = CurrentWeatherSerializer::class)
@Document
data class CurrentWeather(
    @Id
    var _id: ObjectId,
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var current: Current,
) {

    // Add a companion object for the builder
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    // Create a builder class
    data class Builder(
        private var _id: ObjectId? = null,
        private var lat: Double? = null,
        private var lon: Double? = null,
        private var timezone: String? = null,
        private var current: Current? = null
    ) {
        fun _id(lat: Double) = apply { this._id = _id }
        fun lat(lat: Double) = apply { this.lat = lat }
        fun lon(lon: Double) = apply { this.lon = lon }
        fun timezone(timezone: String) = apply { this.timezone = timezone }
        fun current(current: Current) = apply { this.current = current }

        fun build() = CurrentWeather(
            _id ?: ObjectId.get(),
            lat ?: 0.0,
            lon ?: 0.0,
            timezone ?: "",
            current!!
        )
    }
}
