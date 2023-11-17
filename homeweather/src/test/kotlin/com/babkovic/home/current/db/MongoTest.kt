package com.babkovic.home.current.db

import com.babkovic.home.BaseTest
import com.babkovic.home.current.model.domain.Current
import com.babkovic.home.current.model.domain.CurrentWeather
import com.babkovic.home.current.model.repository.CurrentWeatherRepository
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertNotNull

class MongoTest : BaseTest() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(MongoTest::class.java)
    }

    @Autowired
    private lateinit var repo: CurrentWeatherRepository

    @Test
    fun `should perform CRUD operations when db driver is set up correctly`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        val id = ObjectId.get()

        //save entity and check if it is in db
        repo.save(CurrentWeather(id, 10.1, 11.1, "CET", createCurrent())).block()
        assertNotNull(repo.findAll().blockFirst(), "Entity was not saved in the current weather collection")

        //delete entity and check if they were removed
        repo.deleteAll().block()
        assertNull(repo.findAll().blockFirst(), "Entities are not deleted from the current weather collection")

        LOGGER.info("Ending test ${testInfo.displayName}\n")
    }

    private fun createCurrent(): Current {
        return Current.Builder()
            .dt(123456)
            .temp(25.5)
            .pressure(1013)
            .build()
    }
}