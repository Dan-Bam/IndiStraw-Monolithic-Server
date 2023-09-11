package com.project.indistraw.domain.account.application.event

import java.util.*

data class SaveRefreshTokenEvent(
    val refreshToken: String,
    val accountIdx: UUID,
    val expiredAt: Long
)