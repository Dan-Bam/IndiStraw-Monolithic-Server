package com.project.indistraw.domain.account.domain

import java.util.UUID

data class QRCodeUUID(
    val uuid: UUID,
    val expiredAt: Long
)