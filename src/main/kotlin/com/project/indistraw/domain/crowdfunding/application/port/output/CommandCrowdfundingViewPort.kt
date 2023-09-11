package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount

interface CommandCrowdfundingViewPort {

    fun saveCrowdfundingView(crowdfundingViewCount: CrowdfundingViewCount)

}