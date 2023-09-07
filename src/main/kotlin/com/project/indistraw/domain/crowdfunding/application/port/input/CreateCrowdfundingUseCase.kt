package com.project.indistraw.domain.crowdfunding.application.port.input

import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CreateCrowdfundingDto

interface CreateCrowdfundingUseCase {

    fun execute(dto: CreateCrowdfundingDto)

}