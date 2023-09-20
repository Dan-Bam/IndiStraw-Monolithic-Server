package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.UUID

interface QueryCrowdfundingPort {

    fun findByIdxOrNull(idx: Long): Crowdfunding?
    fun findAll(pageRequest: PageRequest): Page<Crowdfunding>
    fun findTop5ByOrderByViewCount(): List<Crowdfunding>
    fun findByWriterIdx(writerIdx: UUID): List<Crowdfunding>
    fun findByTitleOrDescriptionContaining(pageRequest: PageRequest, keyword: String?): Page<Crowdfunding>
    fun findByTitleContaining(keyword: String): List<Crowdfunding>

}