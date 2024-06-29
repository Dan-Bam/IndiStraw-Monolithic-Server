package com.project.indistraw.domain.account.adapter.input.mapper

import com.project.indistraw.domain.account.adapter.input.data.request.QRCodeUUIDRequest
import com.project.indistraw.domain.account.application.port.input.dto.QRCodeUUIDDto
import org.springframework.stereotype.Component

@Component
class QRCodeDataMapper {

    infix fun toDto(request: QRCodeUUIDRequest): QRCodeUUIDDto =
        QRCodeUUIDDto(
            uuid = request.uuid
        )

}