package com.project.indistraw.domain.crowdfunding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.domain.crowdfunding.application.port.input.CrowdfundingDetailUseCase
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.AmountDto
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingDetailDto
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.RewardDto
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.crowdfunding.application.event.QueryCrowdfundingEvent
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.crowdfunding.application.port.output.*
import com.project.indistraw.domain.pay.application.port.output.QueryFundingPort
import org.springframework.context.ApplicationEventPublisher
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@ServiceWithTransaction
class CrowdfundingDetailService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val queryFundingPort: QueryFundingPort,
    private val queryRewardPort: QueryRewardPort,
    private val queryRequestIpPort: QueryRequestIpPort,
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val calculateAmountUtil: CalculateAmountUtil,
    private val publisher: ApplicationEventPublisher
): CrowdfundingDetailUseCase {

    override fun execute(idx: Long): CrowdfundingDetailDto {
        val crowdfunding = queryCrowdfundingPort.findByIdxOrNull(idx)
            ?: throw CrowdfundingNotFoundException()
        val reward = queryRewardPort.findByCrowdfundingIdx(crowdfunding.idx)
        val currentAccountIdx = accountSecurityPort.getCurrentAccountIdx()
        val writer = queryAccountPort.findByIdxOrNull(crowdfunding.writerIdx)
            ?: throw AccountNotFoundException()

        publishEvent(crowdfunding)

        return CrowdfundingDetailDto(
            title = crowdfunding.title,
            description = crowdfunding.description,
            thumbnailUrl = crowdfunding.thumbnailUrl,
            writer = CrowdfundingDetailDto.Writer(
                idx = writer.accountIdx,
                name = writer.name
            ),
            amount = AmountDto(
                targetAmount = crowdfunding.amount.targetAmount,
                totalAmount = crowdfunding.amount.totalAmount,
                percentage = calculateAmountUtil.calculateAmountPercentage(crowdfunding.amount)
            ),
            remainingDay = ChronoUnit.DAYS.between(LocalDate.now(), crowdfunding.endDate),
            fundingCount = queryFundingPort.countByCrowdfundingIdx(crowdfunding.idx),
            imageList = crowdfunding.imageList,
            fileList = crowdfunding.fileList,
            reward = reward.map {
                RewardDto(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    totalCount = it.totalCount,
                    imageList = it.imageList
                )
            },
            isFunding = queryFundingPort.existByOrdererIdxAndCrowdfundingIdx(currentAccountIdx, crowdfunding.idx),
            statusType = crowdfunding.statusType
        )
    }

    private fun publishEvent(crowdfunding: Crowdfunding) {
        val currentIp = queryRequestIpPort.getCurrentRequestIp()
        val queryCrowdfundingEvent = QueryCrowdfundingEvent(crowdfunding, currentIp)
        publisher.publishEvent(queryCrowdfundingEvent)
    }

}
