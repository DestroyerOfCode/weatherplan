package com.babkovic.current.model.repository

import com.babkovic.current.model.domain.CurrentWeather
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CurrentWeatherRepository : ReactiveMongoRepository<CurrentWeather, UUID>