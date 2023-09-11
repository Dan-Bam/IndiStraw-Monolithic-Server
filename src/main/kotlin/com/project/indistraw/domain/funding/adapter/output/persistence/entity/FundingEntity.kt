package com.project.indistraw.domain.funding.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdxEntity
import com.project.indistraw.domain.account.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.RewardEntity
import com.project.indistraw.domain.funding.domain.Funding
import javax.persistence.*

@Entity
@Table(name = "funding")
class FundingEntity(
    @Column(name = "funding_idx", nullable = false)
    override val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crowdfunding_idx")
    val crowdfunding: CrowdfundingEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_idx")
    val reward: RewardEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderer_idx")
    val orderer: AccountEntity,

    @Column(nullable = false)
    val price: Long,

    @Column(nullable = true)
    val extraPrice: Long?,

    @Column(nullable = false)
    val activity: Funding.Activity
): BaseIdxEntity(idx)