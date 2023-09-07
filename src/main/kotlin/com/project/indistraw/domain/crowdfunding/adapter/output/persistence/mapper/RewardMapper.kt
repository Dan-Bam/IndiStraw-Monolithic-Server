package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.RewardEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.crowdfunding.domain.Reward
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

// crowdfunding과 1:N 관계에 걸려있기 때문에 구현을 위해서 전 구현체를 사용한다.
@Component
class RewardMapper(
    private val crowdfundingRepository: CrowdfundingRepository
) {

    infix fun toEntityList(domainList: List<Reward>): List<RewardEntity> {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(domainList[0].crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        return domainList.map {
            RewardEntity(
                idx = it.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                imageList = it.imageList,
                crowdfundingEntity = crowdfundingEntity,
                totalCount = it.totalCount
            )
        }
    }

    infix fun toDomainList(entityList: List<RewardEntity>): List<Reward> {
        return entityList.map {
            Reward(
                idx = it.idx,
                crowdfundingIdx = it.crowdfundingEntity.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                totalCount = it.totalCount,
                imageList = it.imageList
            )
        }
    }

    infix fun toEntity(domain: Reward): RewardEntity {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(domain.crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        return domain.let {
            RewardEntity(
                idx = it.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                imageList = it.imageList,
                crowdfundingEntity = crowdfundingEntity,
                totalCount = it.totalCount
            )
        }
    }

    infix fun toDomain(entity: RewardEntity?): Reward? =
        entity?.let {
            Reward(
                idx = it.idx,
                crowdfundingIdx = it.crowdfundingEntity.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                totalCount = it.totalCount,
                imageList = it.imageList
            )
        }

}