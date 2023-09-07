package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingPagingDto
import org.springframework.data.domain.Pageable

interface SearchCrowdfundingUseCase {

    fun execute(pageable: Pageable, keyword: String?): CrowdfundingPagingDto

}