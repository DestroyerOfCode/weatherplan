package com.babkovic.current.model.repository

import com.babkovic.current.model.domain.CurrentWeather
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository


@Repository
interface CurrentWeatherRepository : ReactiveMongoRepository<CurrentWeather, ObjectId>