package com.project.indistraw.domain.account.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.*
import java.util.concurrent.TimeUnit

@RedisHash("refresh_token")
data class RefreshTokenEntity(
    @Id
    val refreshToken: String,

    val accountIdx: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Long
)