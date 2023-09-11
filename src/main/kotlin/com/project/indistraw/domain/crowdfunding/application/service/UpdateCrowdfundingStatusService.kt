package com.project.indistraw.domain.crowdfunding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.domain.crowdfunding.application.port.input.UpdateCrowdfundingStatusUseCase
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding

@ServiceWithTransaction
class UpdateCrowdfundingStatusService(
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val queryCrowdfundingPort: QueryCrowdfundingPort
): UpdateCrowdfundingStatusUseCase {

    override fun execute(idx: Long, statusType: Crowdfunding.StatusType) {
        val crowdfunding = queryCrowdfundingPort.findByIdxOrNull(idx)
            ?: throw CrowdfundingNotFoundException()

        commandCrowdfundingPort.saveCrowdfunding(
            crowdfunding.updateStatus(statusType)
        )
    }

}