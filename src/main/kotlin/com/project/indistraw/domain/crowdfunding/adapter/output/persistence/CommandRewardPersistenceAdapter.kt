package com.project.indistraw.domain.crowdfunding.adapter.output.persistence

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper.RewardMapper
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.RewardRepository
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandRewardPort
import com.project.indistraw.domain.crowdfunding.domain.Reward
import org.springframework.stereotype.Component

@Component
class CommandRewardPersistenceAdapter(
    private val rewardRepository: RewardRepository,
    private val rewardMapper: RewardMapper
): CommandRewardPort {

    override fun saveReward(reward: Reward) {
        val rewardEntity = rewardMapper toEntity reward
        rewardRepository.save(rewardEntity)
    }

    override fun saveAllReword(rewordList: List<Reward>) {
        val rewordEntityList = rewardMapper toEntityList rewordList
        rewardRepository.saveAll(rewordEntityList)
    }

}