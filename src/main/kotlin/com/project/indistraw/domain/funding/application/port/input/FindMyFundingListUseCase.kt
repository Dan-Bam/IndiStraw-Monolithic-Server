package com.project.indistraw.domain.funding.application.port.input

import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingListDto

interface FindMyFundingListUseCase {

    fun execute(): List<CrowdfundingListDto>

}