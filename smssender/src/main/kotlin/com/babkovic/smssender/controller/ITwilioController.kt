package com.babkovic.smssender.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/api/twilio")
@ResponseBody
interface ITwilioController {
    @GetMapping("/bulk/send")
    fun saveCurrentWeather(): ResponseEntity<Unit>
}
