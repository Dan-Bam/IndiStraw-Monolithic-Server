package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.RewardEntity
import org.springframework.data.repository.CrudRepository

interface RewardRepository: CrudRepository<RewardEntity, Long> {

    fun findByCrowdfundingEntity(crowdfundingEntity: CrowdfundingEntity): List<RewardEntity>

}