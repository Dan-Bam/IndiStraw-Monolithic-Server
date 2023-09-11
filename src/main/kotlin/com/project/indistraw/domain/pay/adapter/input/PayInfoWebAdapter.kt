package com.project.indistraw.domain.pay.adapter.input

import com.project.indistraw.domain.pay.application.port.input.CreatePayInfoUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/pay")
class PayInfoWebAdapter(
    private val createPayInfoUseCase: CreatePayInfoUseCase
) {

    @PostMapping("receipt")
    fun createPayInfo(): ResponseEntity<Map<String, String>> =
        createPayInfoUseCase.execute()
            .let { ResponseEntity.ok(mapOf("receiptId" to it)) }

}