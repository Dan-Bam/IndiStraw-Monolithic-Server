package com.project.indistraw.domain.crowdfunding.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot

@AggregateRoot
data class CrowdfundingViewCount(
    val crowdfundingIdx: Long,
    val ips: Set<String>
) {

    fun addIp(ip: String): CrowdfundingViewCount =
        this.copy(ips = this.ips + ip)

}