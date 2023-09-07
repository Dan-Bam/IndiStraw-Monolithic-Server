package com.project.indistraw.domain.account.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.QRCodeUUIDMapper
import com.project.indistraw.domain.account.adapter.output.persistence.repository.QRCodeUUIDRepository
import com.project.indistraw.domain.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.domain.account.domain.QRCodeUUID
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryQRCodeUUIDPersistenceAdapter(
    private val qrCodeUUIDRepository: QRCodeUUIDRepository,
    private val qrCodeUUIDMapper: QRCodeUUIDMapper
): QueryQRCodeUUIDPort {

    override fun findByUUID(uuid: UUID): QRCodeUUID? {
        val qrCodeUUIDEntity = qrCodeUUIDRepository.findByIdOrNull(uuid)
        return qrCodeUUIDMapper toDomain qrCodeUUIDEntity
    }

    override fun existByUUID(uuid: UUID): Boolean {
        return qrCodeUUIDRepository.existsById(uuid)
    }

}