package com.project.indistraw.domain.account.application.port.input

import java.util.UUID

interface CreateQRCodeUUIDUseCase {

    fun execute(): UUID

}