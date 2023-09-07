package com.project.indistraw.domain.account.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.UUID
import java.util.concurrent.TimeUnit

@RedisHash
data class QRCodeUUIDEntity(
    @Id
    val uuid: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Long
)