package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdxEntity
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "reward")
class RewardEntity(
    @Column(name = "reward_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val price: Long,

    @ElementCollection
    @CollectionTable(name = "reword_image", joinColumns = [JoinColumn(name = "reword_idx")])
    @Column(name = "image_url", columnDefinition = "TEXT", nullable = false)
    val imageList: List<String>,

    @Column(nullable = true)
    val totalCount: Long?,

    @Cascade(CascadeType.DELETE)
    @ManyToOne(fetch = FetchType.LAZY)
    val crowdfundingEntity: CrowdfundingEntity
): BaseIdxEntity(idx)