package com.project.indistraw.global.health

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckWebAdapter {

    @GetMapping
    fun healthCheck(): ResponseEntity<Map<String, String>> =
        ResponseEntity.ok(mapOf("message" to "IndiStraw server running"))

}