package com.babkovic.health.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckControllerImpl : HealthCheckController {

    override fun checkHealth(): ResponseEntity<String> {
        return ResponseEntity.ok("I am okay\n")
    }
}