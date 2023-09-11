package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdxEntity
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "crowdfunding")
class CrowdfundingEntity(
    @Column(name = "crowdfunding_idx", nullable = false)
    override val idx: Long,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val writerIdx: UUID,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Embedded
    val amount: AmountEntity,

    @Embedded
    val directorAccount: DirectorAccountEntity,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime,

    @Column(nullable = false)
    val endDate: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val statusType: Crowdfunding.StatusType,

    @Column(nullable = false)
    val viewCount: Long,

    @Column(nullable = false)
    val thumbnailUrl: String,

    @ElementCollection
    @CollectionTable(name = "crowdfunding_image", joinColumns = [JoinColumn(name = "crowdfunding_idx")])
    @Column(name = "image_url", columnDefinition = "TEXT", nullable = false)
    val imageList: List<String>,

    @ElementCollection
    @CollectionTable(name = "crowdfunding_file", joinColumns = [JoinColumn(name = "crowdfunding_idx")])
    @Column(name = "file_url", columnDefinition = "TEXT", nullable = false)
    val fileList: List<String>,
): BaseIdxEntity(idx)