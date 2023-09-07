package com.project.indistraw.domain.account.domain

import java.util.UUID

data class RefreshToken(
    val refreshToken: String,
    val accountIdx: UUID,
    val expiredAt: Long
)