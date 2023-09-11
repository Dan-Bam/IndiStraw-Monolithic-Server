package com.project.indistraw.domain.funding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.crowdfunding.application.exception.RewardNotFoundException
import com.project.indistraw.domain.funding.application.port.input.SaveFundingUseCase
import com.project.indistraw.domain.funding.application.port.input.dto.SaveFundingDto
import com.project.indistraw.domain.crowdfunding.application.port.output.*
import com.project.indistraw.domain.funding.application.port.output.CommandFundingPort
import com.project.indistraw.domain.funding.domain.Funding
import com.project.indistraw.domain.pay.application.port.output.PayPort

@ServiceWithTransaction
class SaveFundingService(
    private val commandFundingPort: CommandFundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val queryRewardPort: QueryRewardPort,
    private val commandRewardPort: CommandRewardPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val payPort: PayPort
): SaveFundingUseCase {

    override fun execute(dto: SaveFundingDto) {
        // TODO(payPort의 의존성을 삭제하고 event로 바꾸어 결제 서비스가 이상하더라도 작업이 되게 한다.)
        // receiptId를 가지고 bootpay 결제 정보 검증 요청을 합니다.
        payPort.confirm(dto.receiptId)

        val orderer = queryAccountPort.findByIdxOrNull(accountSecurityPort.getCurrentAccountIdx())
            ?: throw AccountNotFoundException()
        var crowdfunding = queryCrowdfundingPort.findByIdxOrNull(dto.crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        var reward = queryRewardPort.findByIdx(dto.rewordIdx)
            ?: throw RewardNotFoundException()
        val funding = Funding(
            idx = 0L,
            crowdfunding = crowdfunding,
            reword = reward,
            orderer = orderer,
            price = dto.price,
            extraPrice = dto.extraPrice,
            activity = Funding.Activity.DEPOSITED
        )
        commandFundingPort.saveFunding(funding)

        if (dto.extraPrice == null) {
            crowdfunding = crowdfunding.increaseTotalAmount(dto.price)
        }
        crowdfunding = crowdfunding.increaseTotalAmount(dto.price.plus(dto.extraPrice!!))
        reward = reward.deductionTotalCount()

        commandCrowdfundingPort.saveCrowdfunding(crowdfunding)
        commandRewardPort.saveReward(reward)
    }

}