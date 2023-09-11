package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("crowdfunding_view_count")
data class CrowdfundingViewCountEntity(
    @Id
    val crowdfundingIdx: Long,

    val ips: Set<String>
)