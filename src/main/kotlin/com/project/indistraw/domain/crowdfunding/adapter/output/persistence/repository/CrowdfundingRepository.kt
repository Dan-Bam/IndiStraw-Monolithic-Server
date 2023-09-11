package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CrowdfundingRepository: JpaRepository<CrowdfundingEntity, Long> {

    fun findTop5ByStatusTypeNotOrderByViewCountDesc(statusType: Crowdfunding.StatusType): List<CrowdfundingEntity>
    fun findByWriterIdx(writerIdx: UUID): List<CrowdfundingEntity>
    fun findAllByStatusTypeNot(statusType: Crowdfunding.StatusType, pageable: Pageable): Page<CrowdfundingEntity>

}