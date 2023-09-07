package com.project.indistraw.domain.crowdfunding.application.port.input

import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingDetailDto

interface CrowdfundingDetailUseCase {

    fun execute(idx: Long): CrowdfundingDetailDto

}