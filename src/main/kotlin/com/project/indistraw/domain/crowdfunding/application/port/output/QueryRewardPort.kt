package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.domain.Reward

interface QueryRewardPort {

    fun findByIdx(idx: Long): Reward?
    fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reward>

}