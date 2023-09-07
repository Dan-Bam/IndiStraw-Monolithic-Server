package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.QRCodeUUIDDto

interface QRCodeSignInUseCase {

    fun execute(dto: QRCodeUUIDDto)

}