package com.project.indistraw.domain.crowdfunding.adapter.output.persistence

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.QCrowdfundingEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper.CrowdfundingMapper
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryCrowdfundingPersistenceAdapter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val queryFactory: JPAQueryFactory,
    private val crowdfundingMapper: CrowdfundingMapper
): QueryCrowdfundingPort {

    override fun findByIdxOrNull(idx: Long): Crowdfunding? {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(idx)
        return crowdfundingMapper toDomain crowdfundingEntity
    }

    override fun findAll(pageRequest: PageRequest): Page<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findAllByStatusTypeNot(Crowdfunding.StatusType.UNDER_REVIEW, pageRequest)
        return crowdfundingList.map { crowdfundingMapper toDomain it }
    }

    override fun findTop5ByOrderByViewCount(): List<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findTop5ByStatusTypeNotOrderByViewCountDesc(Crowdfunding.StatusType.UNDER_REVIEW)
        return crowdfundingList.map { (crowdfundingMapper toDomain it)!! }
    }

    override fun findByWriterIdx(writerIdx: UUID): List<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findByWriterIdx(writerIdx)
        return crowdfundingList.map { (crowdfundingMapper toDomain it)!! }
    }

    override fun findByTitleOrDescriptionContaining(pageRequest: PageRequest, keyword: String?): Page<Crowdfunding> {
        val result = queryFactory.selectFrom(QCrowdfundingEntity.crowdfundingEntity)
            .where(eqKeyword(keyword))
            .orderBy(QCrowdfundingEntity.crowdfundingEntity.createdAt.desc())
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()
        val crowdfundingEntityList = PageImpl(result, pageRequest, result.size.toLong())
        val crowdfundingList = crowdfundingEntityList.map { (crowdfundingMapper toDomain it)!! }
        return PageImpl(crowdfundingList.toList(), crowdfundingEntityList.pageable, crowdfundingEntityList.size.toLong())
    }

    private fun eqKeyword(keyword: String?): BooleanExpression? {
        if (keyword == null) return null
        return QCrowdfundingEntity.crowdfundingEntity.title.like("%$keyword$").or(QCrowdfundingEntity.crowdfundingEntity.description.like("%$keyword$"))
    }

}