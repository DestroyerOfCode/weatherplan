package com.babkovic.home.current.model.repository

import com.babkovic.home.current.model.domain.CurrentWeather
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository


@Repository
interface CurrentWeatherRepository : ReactiveMongoRepository<CurrentWeather, ObjectId>