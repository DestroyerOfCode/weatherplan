package com.babkovic.openweather

import com.babkovic.BaseTest
import com.babkovic.current.model.domain.Current
import com.babkovic.current.model.domain.CurrentWeather
import com.babkovic.current.model.repository.CurrentWeatherRepository
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Mono

class MongoTest : BaseTest() {

    @Autowired
    private lateinit var repo: CurrentWeatherRepository

    @Test
    fun basicMongoTest() {
        val _id = ObjectId.get()
        val currentWeather: Mono<CurrentWeather> = repo.save(CurrentWeather(_id, 10.1, 11.1, "CET", createCurrent()))
        currentWeather.subscribe()
        Assertions.assertNotNull(currentWeather)
        repo.findAll()
//        repo.findById(curren)

    }

    private fun createCurrent(): Current {
        return Current.Builder()
            .dt(123456)
            .temp(25.5)
            .pressure(1013)
            .build()
    }
}