package com.project.indistraw.domain.funding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.domain.funding.application.port.input.FindMyFundingListUseCase
import com.project.indistraw.domain.pay.application.port.output.QueryFundingPort

@ServiceWithReadOnlyTransaction
class FindMyFundingListService(
    private val queryFundingPort: QueryFundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val calculateAmountUtil: CalculateAmountUtil
): FindMyFundingListUseCase {

    override fun execute(): List<CrowdfundingListDto> {
        val ordererIdx = accountSecurityPort.getCurrentAccountIdx()
        val fundingList = queryFundingPort.findByOrdererIdx(ordererIdx)
        return fundingList.map {
            CrowdfundingListDto(
                idx = it.idx,
                title = it.crowdfunding.title,
                description = it.crowdfunding.description,
                percentage = calculateAmountUtil.calculateAmountPercentage(it.crowdfunding.amount),
                thumbnailUrl = it.crowdfunding.thumbnailUrl,
                statusType = it.crowdfunding.statusType
            )
        }
    }

}