package com.project.indistraw.domain.crowdfunding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.domain.crowdfunding.application.port.input.FindMyCrowdfundingListUseCase
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort

@ServiceWithReadOnlyTransaction
class FindMyCrowdfundingListService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val calculateAmountUtil: CalculateAmountUtil
): FindMyCrowdfundingListUseCase {

    override fun execute(): List<CrowdfundingListDto> {
        val writerIdx = accountSecurityPort.getCurrentAccountIdx()
        val crowdfundingList = queryCrowdfundingPort.findByWriterIdx(writerIdx)
        return crowdfundingList.map {
            CrowdfundingListDto(
                idx = it.idx,
                title = it.title,
                description = it.description,
                percentage = calculateAmountUtil.calculateAmountPercentage(it.amount),
                thumbnailUrl = it.thumbnailUrl,
                statusType = it.statusType
            )
        }
    }

}