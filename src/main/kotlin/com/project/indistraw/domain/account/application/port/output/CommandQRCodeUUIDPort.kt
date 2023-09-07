package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.QRCodeUUID

interface CommandQRCodeUUIDPort {

    fun saveQRCodeUUID(domain: QRCodeUUID)

}