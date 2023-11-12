package com.babkovic.smssender.controller

import com.babkovic.smssender.service.ITwilioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TwilioControllerImpl(private val twilioService: ITwilioService) : ITwilioController {
    override fun saveCurrentWeather(): ResponseEntity<Unit> {
        return ResponseEntity.ok(twilioService.sendCurrentWeatherSms())
    }

}