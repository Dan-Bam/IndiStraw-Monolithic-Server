package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.input.UpdateAccountInfoUseCase
import com.project.indistraw.domain.account.application.port.input.dto.UpdateAccountInfoDto
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort

@ServiceWithTransaction
class UpdateAccountInfoService(
    private val queryAccountPort: QueryAccountPort,
    private val commandAccountPort: CommandAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): UpdateAccountInfoUseCase {

    override fun execute(dto: UpdateAccountInfoDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        commandAccountPort.saveAccount(
            account.updateInfo(name = dto.name, profileUrl = dto.profileUrl)
        )
    }
}