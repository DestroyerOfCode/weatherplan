package com.babkovic.smssender.model.domain

import com.babkovic.openweather.model.domain.Coord
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class User(
    @Id
    @JsonProperty("_id")
    var id: ObjectId,
    var userName: String,
    var phoneNumber: String,
    var password: String,
    var coord: Coord,
)
