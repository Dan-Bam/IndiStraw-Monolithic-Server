package com.project.indistraw.domain.crowdfunding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.crowdfunding.application.port.input.CreateCrowdfundingUseCase
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CreateCrowdfundingDto
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandRewardPort
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import com.project.indistraw.domain.crowdfunding.domain.Reward
import java.time.LocalDateTime

@ServiceWithTransaction
class CreateCrowdfundingService(
    private val accountSecurityPort: AccountSecurityPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val commandRewordPort: CommandRewardPort
): CreateCrowdfundingUseCase {

    override fun execute(dto: CreateCrowdfundingDto) {
        // dto값을 토대로 crowdfunding 객체를 생성하여 저장합니다.
        var crowdfunding = Crowdfunding(
            idx = 0L,
            writerIdx = accountSecurityPort.getCurrentAccountIdx(),
            title = dto.title,
            description = dto.description,
            amount = Crowdfunding.Amount(
                targetAmount = dto.targetAmount,
                totalAmount = 0L
            ),
            directorAccount = Crowdfunding.DirectorAccount(
                bank = dto.directorAccount.bank,
                account = dto.directorAccount.account
            ),
            createdAt = LocalDateTime.now(),
            endDate = dto.endDate,
            viewCount = 0,
            statusType = Crowdfunding.StatusType.UNDER_REVIEW,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            fileList = dto.fileList
        )
        crowdfunding = commandCrowdfundingPort.saveCrowdfunding(crowdfunding)

        // dto값을 토대로 reword 객체를 생성하여 저장합니다.
        val rewordList = dto.reward.map {
            Reward(
                idx = 0L,
                crowdfundingIdx = crowdfunding.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                totalCount = it.totalCount,
                imageList = it.imageList
            )
        }
        commandRewordPort.saveAllReword(rewordList)
    }

}