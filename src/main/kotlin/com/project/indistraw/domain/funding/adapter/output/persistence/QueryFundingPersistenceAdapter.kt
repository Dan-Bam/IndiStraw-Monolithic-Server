package com.project.indistraw.domain.funding.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.entity.QAccountEntity
import com.project.indistraw.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.QCrowdfundingEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.funding.adapter.output.persistence.entity.QFundingEntity
import com.project.indistraw.domain.funding.adapter.output.persistence.mapper.FundingMapper
import com.project.indistraw.domain.funding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.domain.funding.domain.Funding
import com.project.indistraw.domain.pay.application.port.output.QueryFundingPort
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryFundingPersistenceAdapter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val fundingRepository: FundingRepository,
    private val queryFactory: JPAQueryFactory,
    private val fundingMapper: FundingMapper,
    private val accountMapper: AccountMapper
): QueryFundingPort {

    override fun findByIdx(idx: Long): Funding? {
        val fundingEntity = fundingRepository.findByIdOrNull(idx)
        return fundingMapper toDomain fundingEntity
    }

    override fun existByOrdererIdxAndCrowdfundingIdx(ordererIdx: UUID, crowdfundingIdx: Long): Boolean {
        return fundingRepository.existsByOrdererAccountIdxAndCrowdfundingIdx(ordererIdx, crowdfundingIdx)
    }

    override fun countByCrowdfundingIdx(crowdfundingIdx: Long): Long {
        val crowdfunding = crowdfundingRepository.findByIdOrNull(crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        return fundingRepository.countByCrowdfunding(crowdfunding)
    }

    override fun findOrdererByCrowdfundingIdx(crowdfundingIdx: Long): List<Account> =
        queryFactory.selectDistinct(QAccountEntity.accountEntity)
            .from(QFundingEntity.fundingEntity)
            .join(QFundingEntity.fundingEntity.crowdfunding, QCrowdfundingEntity.crowdfundingEntity)
            .join(QFundingEntity.fundingEntity.orderer, QAccountEntity.accountEntity)
            .where(QFundingEntity.fundingEntity.crowdfunding.idx.eq(crowdfundingIdx))
            .fetch()
            .map { (accountMapper.toDomain(it)!!) }

    override fun findByOrdererIdx(ordererIdx: UUID): List<Funding> {
        return queryFactory.selectDistinct(QFundingEntity.fundingEntity)
            .from(QFundingEntity.fundingEntity)
            .join(QFundingEntity.fundingEntity.crowdfunding, QCrowdfundingEntity.crowdfundingEntity)
            .join(QFundingEntity.fundingEntity.orderer, QAccountEntity.accountEntity)
            .where(QAccountEntity.accountEntity.accountIdx.eq(ordererIdx))
            .fetch()
            .map { (fundingMapper toDomain it)!! }
    }

}