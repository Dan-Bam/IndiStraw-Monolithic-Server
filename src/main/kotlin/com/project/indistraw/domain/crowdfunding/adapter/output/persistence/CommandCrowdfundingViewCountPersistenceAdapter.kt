package com.project.indistraw.domain.crowdfunding.adapter.output.persistence

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper.CrowdfundingMapper
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingViewCountRepository
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandCrowdfundingViewPort
import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount
import org.springframework.stereotype.Component

@Component
class CommandCrowdfundingViewCountPersistenceAdapter(
    private val crowdfundingViewCountRepository: CrowdfundingViewCountRepository,
    private val crowdfundingMapper: CrowdfundingMapper
): CommandCrowdfundingViewPort {

    override fun saveCrowdfundingView(crowdfundingViewCount: CrowdfundingViewCount) {
        val crowdfundingViewCountEntity = crowdfundingMapper toEntity crowdfundingViewCount
        crowdfundingViewCountRepository.save(crowdfundingViewCountEntity)
    }

}