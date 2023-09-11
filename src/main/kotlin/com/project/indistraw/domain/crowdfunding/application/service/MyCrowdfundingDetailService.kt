package com.project.indistraw.domain.crowdfunding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.crowdfunding.application.exception.NotMyCrowdfundingException
import com.project.indistraw.domain.crowdfunding.application.port.input.MyCrowdfundingDetailUseCase
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.AmountDto
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.MyCrowdfundingDetailDto
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.OrdererDto
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.RewardDto
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.domain.pay.application.port.output.QueryFundingPort
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryRewardPort
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@ServiceWithReadOnlyTransaction
class MyCrowdfundingDetailService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val queryFundingPort: QueryFundingPort,
    private val queryRewardPort: QueryRewardPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val calculateAmountUtil: CalculateAmountUtil
): MyCrowdfundingDetailUseCase {

    override fun execute(idx: Long): MyCrowdfundingDetailDto {
        val crowdfunding = queryCrowdfundingPort.findByIdxOrNull(idx)
            ?: throw CrowdfundingNotFoundException()

        // 현재 사용자가 작성한 크라우드 펀딩인지 검증합니다.
        verifyMyCrowdfunding(crowdfunding.writerIdx)

        val rewardList = queryRewardPort.findByCrowdfundingIdx(crowdfunding.idx)
        val ordererList = queryFundingPort.findOrdererByCrowdfundingIdx(crowdfunding.idx)

        return MyCrowdfundingDetailDto(
            title = crowdfunding.title,
            thumbnailUrl = crowdfunding.thumbnailUrl,
            amount = AmountDto(
                targetAmount = crowdfunding.amount.targetAmount,
                totalAmount = crowdfunding.amount.totalAmount,
                percentage = calculateAmountUtil.calculateAmountPercentage(crowdfunding.amount)
            ),
            remainingDay = ChronoUnit.DAYS.between(LocalDate.now(), crowdfunding.endDate),
            fundingCount = queryFundingPort.countByCrowdfundingIdx(crowdfunding.idx),
            reward = rewardList.map {
                RewardDto(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    totalCount = it.totalCount,
                    imageList = it.imageList
                )
            },
            orderer = ordererList.map {
                OrdererDto(
                    accountIdx = it.accountIdx,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    zipcode = it.address?.zipcode,
                    address = it.address?.let { address ->
                        if (address.zipcode.isNullOrBlank()) null
                        else address.streetAddress + " " + address.detailAddress
                    },
                    profileUrl = it.profileUrl
                )
            }
        )
    }

    private fun verifyMyCrowdfunding(writerIdx: UUID) {
        val currentAccountIdx = accountSecurityPort.getCurrentAccountIdx()
        if (currentAccountIdx != writerIdx) {
            throw NotMyCrowdfundingException()
        }
    }

}