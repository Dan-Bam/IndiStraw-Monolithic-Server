package com.project.indistraw.domain.pay.application.port.output

import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.funding.domain.Funding
import java.util.UUID

interface QueryFundingPort {

    fun findByIdx(idx: Long): Funding?
    fun existByOrdererIdxAndCrowdfundingIdx(ordererIdx: UUID, crowdfundingIdx: Long): Boolean
    fun countByCrowdfundingIdx(crowdfundingIdx: Long): Long
    fun findOrdererByCrowdfundingIdx(crowdfundingIdx: Long): List<Account>
    fun findByOrdererIdx(ordererIdx: UUID): List<Funding>

}