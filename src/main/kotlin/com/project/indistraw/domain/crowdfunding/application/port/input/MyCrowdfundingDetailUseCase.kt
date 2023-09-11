package com.project.indistraw.domain.crowdfunding.application.port.input

import com.project.indistraw.domain.crowdfunding.application.port.input.dto.MyCrowdfundingDetailDto

interface MyCrowdfundingDetailUseCase {

    fun execute(idx: Long): MyCrowdfundingDetailDto

}