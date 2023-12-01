package com.babkovic.home.current.model.domain

import com.babkovic.home.current.mapper.CurrentWeatherDeserializer
import com.babkovic.home.current.mapper.CurrentWeatherSerializer
import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("_id")
    var id: ObjectId,
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var current: Current,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    data class Builder(
        private var id: ObjectId? = null,
        private var lat: Double? = null,
        private var lon: Double? = null,
        private var timezone: String? = null,
        private var current: Current? = null
    ) {
        fun id(id: ObjectId) = apply { this.id = id }
        fun lat(lat: Double) = apply { this.lat = lat }
        fun lon(lon: Double) = apply { this.lon = lon }
        fun timezone(timezone: String) = apply { this.timezone = timezone }
        fun current(current: Current) = apply { this.current = current }

        fun build() = CurrentWeather(
            id ?: ObjectId.get(),
            lat ?: 0.0,
            lon ?: 0.0,
            timezone ?: "",
            current!!
        )
    }
}
