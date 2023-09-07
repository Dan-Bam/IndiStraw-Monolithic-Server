package com.project.indistraw.domain.account.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.QRCodeUUIDMapper
import com.project.indistraw.domain.account.adapter.output.persistence.repository.QRCodeUUIDRepository
import com.project.indistraw.domain.account.application.port.output.CommandQRCodeUUIDPort
import com.project.indistraw.domain.account.domain.QRCodeUUID
import org.springframework.stereotype.Component

@Component
class CommandQRCodeUUIDPersistenceAdapter(
    private val qrCodeUUIDRepository: QRCodeUUIDRepository,
    private val qrCodeUUIDMapper: QRCodeUUIDMapper
): CommandQRCodeUUIDPort {

    override fun saveQRCodeUUID(domain: QRCodeUUID) {
        val qrCodeUUIDEntity = qrCodeUUIDMapper toEntity domain
        qrCodeUUIDRepository.save(qrCodeUUIDEntity)
    }

}