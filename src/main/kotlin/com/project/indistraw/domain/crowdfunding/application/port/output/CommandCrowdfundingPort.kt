package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding

interface CommandCrowdfundingPort {

    fun saveCrowdfunding(crowdfunding: Crowdfunding): Crowdfunding

}