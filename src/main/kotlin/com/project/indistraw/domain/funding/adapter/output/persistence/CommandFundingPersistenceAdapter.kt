package com.project.indistraw.domain.funding.adapter.output.persistence

import com.project.indistraw.domain.funding.application.port.output.CommandFundingPort
import com.project.indistraw.domain.funding.adapter.output.persistence.mapper.FundingMapper
import com.project.indistraw.domain.funding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.domain.funding.domain.Funding
import org.springframework.stereotype.Component

@Component
class CommandFundingPersistenceAdapter(
    private val fundingRepository: FundingRepository,
    private val fundingMapper: FundingMapper
): CommandFundingPort {

    override fun saveFunding(funding: Funding) {
        val entity = fundingMapper toEntity funding
        fundingRepository.save(entity)
    }

    override fun deleteFunding(funding: Funding) {
        val entity = fundingMapper toEntity funding
        fundingRepository.delete(entity)
    }

}