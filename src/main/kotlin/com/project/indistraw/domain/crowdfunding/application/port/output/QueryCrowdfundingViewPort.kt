package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount

interface QueryCrowdfundingViewPort {

    fun existsByIdx(crowdfundingIdx: Long): Boolean
    fun findByIdx(crowdfundingIdx: Long): CrowdfundingViewCount

}