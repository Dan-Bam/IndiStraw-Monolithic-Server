package com.project.indistraw.domain.crowdfunding.adapter.output.persistence

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper.RewardMapper
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.RewardRepository
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryRewardPort
import com.project.indistraw.domain.crowdfunding.domain.Reward
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryRewardPersistenceAdapter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val rewardRepository: RewardRepository,
    private val rewardMapper: RewardMapper
): QueryRewardPort {

    override fun findByIdx(idx: Long): Reward? {
        val rewardEntity = rewardRepository.findByIdOrNull(idx)
        return rewardMapper toDomain rewardEntity
    }

    override fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reward> {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        val rewardList = rewardRepository.findByCrowdfundingEntity(crowdfundingEntity)
        return rewardMapper toDomainList rewardList
    }

}