package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.QRCodeUUID
import java.util.*

interface QueryQRCodeUUIDPort {

    fun findByUUID(uuid: UUID): QRCodeUUID?
    fun existByUUID(uuid: UUID): Boolean

}