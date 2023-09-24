package com.babkovic.common.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/health")
interface HealthCheckController {

    @GetMapping
    fun checkHealth(): ResponseEntity<String>
}
