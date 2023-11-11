package com.babkovic.openweather.health

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/health")
interface HealthCheckController {

    @GetMapping
    fun checkHealth(): ResponseEntity<String>
}
