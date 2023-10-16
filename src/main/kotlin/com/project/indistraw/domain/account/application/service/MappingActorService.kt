package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.input.MappingActorUseCase
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.movie.application.exception.ActorNotFoundException
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort

@ServiceWithTransaction
class MappingActorService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val queryActorPort: QueryActorPort,
    private val commandAccountPort: CommandAccountPort
): MappingActorUseCase {

    override fun execute(idx: Long) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx) ?: throw AccountNotFoundException()
        if (!queryActorPort.existsByIdx(idx)) {
            throw ActorNotFoundException()
        }
        val updateAccount = account.addActor(idx)
        commandAccountPort.saveAccount(updateAccount)
    }

}