package com.babkovic.smssender.controller

import com.babkovic.smssender.BaseTest
import com.babkovic.smssender.model.domain.Coord
import com.babkovic.smssender.model.domain.User
import com.babkovic.smssender.model.repository.UserRepository
import org.bson.types.ObjectId
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

class TwilioControllerImplIT : BaseTest() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(TwilioControllerImplIT::class.java)
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeAll
    fun setUp() {
        userRepository.insert(
            User(
                ObjectId.get(),
                "Gregorz Brzęczyśczikiewicz",
                "+421950898744",
                "admin",
                Coord(10.2, 12.3)
            )
        )
            .subscribe()
        userRepository.insert(User(ObjectId.get(), "Zbigniew", "+421950898744", "admin", Coord(10.2, 12.3))).subscribe()
    }

    @AfterAll
    fun cleanUp() {
        userRepository.deleteAll().subscribe()
    }

    @Test
    fun `should get current weather of user and send sms if active`(testInfo: TestInfo) {
        LOGGER.info("Starting test ${testInfo.displayName}\n")

        //given
        val url = "$GATEWAY_URL/twilio/bulk/send"

        //when & then
        assertDoesNotThrow({
            client.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(ResponseEntity::class.java)
        }, "An exception was thrown from ${testInfo.displayName}")

        LOGGER.info("Ending test ${testInfo.displayName}\n")
    }
}