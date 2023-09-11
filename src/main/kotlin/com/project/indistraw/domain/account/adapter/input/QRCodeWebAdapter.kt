package com.project.indistraw.domain.account.adapter.input

import com.project.indistraw.domain.account.adapter.input.data.request.QRCodeUUIDRequest
import com.project.indistraw.domain.account.adapter.input.mapper.QRCodeDataMapper
import com.project.indistraw.domain.account.application.port.input.ConnectSseUseCase
import com.project.indistraw.domain.account.application.port.input.CreateQRCodeUUIDUseCase
import com.project.indistraw.domain.account.application.port.input.QRCodeSignInUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/qr-code")
class QRCodeWebAdapter(
    private val qrCodeDataMapper: QRCodeDataMapper,
    private val createQRCodeUUIDUseCase: CreateQRCodeUUIDUseCase,
    private val connectSseUseCase: ConnectSseUseCase,
    private val qrCodeSignInUseCase: QRCodeSignInUseCase,
) {

    @PostMapping
    fun createUUID(): ResponseEntity<Map<String, UUID>> =
        createQRCodeUUIDUseCase.execute()
            .let { ResponseEntity.ok(mapOf("uuid" to it)) }

    @GetMapping(value = ["/connect/{uuid}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun sseConnect(@PathVariable uuid: UUID): ResponseEntity<SseEmitter> =
        connectSseUseCase.execute(uuid)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/signin")
    fun qrCodeSignIn(@RequestBody @Valid request: QRCodeUUIDRequest): ResponseEntity<Void> =
        qrCodeSignInUseCase.execute(qrCodeDataMapper toDto request)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}