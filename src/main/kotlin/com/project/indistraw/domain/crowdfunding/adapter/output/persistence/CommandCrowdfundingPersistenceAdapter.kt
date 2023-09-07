package com.project.indistraw.domain.crowdfunding.adapter.output.persistence

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper.CrowdfundingMapper
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import org.springframework.stereotype.Component

@Component
class CommandCrowdfundingPersistenceAdapter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val crowdfundingMapper: CrowdfundingMapper
): CommandCrowdfundingPort {

    override fun saveCrowdfunding(crowdfunding: Crowdfunding): Crowdfunding {
        val crowdfundingEntity = crowdfundingMapper toEntity crowdfunding
        return (crowdfundingMapper toDomain crowdfundingRepository.save(crowdfundingEntity))!!
    }

}