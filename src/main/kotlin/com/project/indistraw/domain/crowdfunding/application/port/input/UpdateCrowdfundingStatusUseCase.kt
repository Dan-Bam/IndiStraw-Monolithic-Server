package com.project.indistraw.domain.crowdfunding.application.port.input

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding

interface UpdateCrowdfundingStatusUseCase {

    fun execute(idx: Long, statusType: Crowdfunding.StatusType)

}