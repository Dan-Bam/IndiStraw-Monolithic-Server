package com.project.indistraw.domain.crowdfunding.adapter.output.persistence

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper.CrowdfundingViewCountMapper
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingViewCountRepository
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingViewPort
import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryCrowdfundingViewCountPersistenceAdapter(
    private val crowdfundingViewCountRepository: CrowdfundingViewCountRepository,
    private val crowdfundingViewCountMapper: CrowdfundingViewCountMapper
): QueryCrowdfundingViewPort {

    override fun existsByIdx(crowdfundingIdx: Long): Boolean {
        return crowdfundingViewCountRepository.existsById(crowdfundingIdx)
    }

    override fun findByIdx(crowdfundingIdx: Long): CrowdfundingViewCount {
        val crowdfundingViewCountEntity = crowdfundingViewCountRepository.findByIdOrNull(crowdfundingIdx)!!
        return crowdfundingViewCountMapper toDomain crowdfundingViewCountEntity
    }

}