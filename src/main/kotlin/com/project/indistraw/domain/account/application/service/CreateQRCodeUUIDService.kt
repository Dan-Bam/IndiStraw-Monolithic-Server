package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.port.input.CreateQRCodeUUIDUseCase
import com.project.indistraw.domain.account.application.port.output.CommandQRCodeUUIDPort
import com.project.indistraw.domain.account.domain.QRCodeUUID
import java.util.*

@ServiceWithTransaction
class CreateQRCodeUUIDService(
    private val commandQRCodeUUIDPort: CommandQRCodeUUIDPort
): CreateQRCodeUUIDUseCase {

    override fun execute(): UUID {
        val uuid = UUID.randomUUID()
        val qrCodeUUID = QRCodeUUID(
            uuid = uuid,
            expiredAt = 300L
        )
        commandQRCodeUUIDPort.saveQRCodeUUID(qrCodeUUID)

        return qrCodeUUID.uuid
    }

}