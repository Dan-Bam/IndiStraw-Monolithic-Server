package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.exception.AlreadyMappingDirectorException
import com.project.indistraw.domain.account.application.port.input.MappingDirectorUseCase
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.movie.application.exception.DirectorNotFoundException
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort

@ServiceWithTransaction
class MappingDirectorService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val queryDirectorPort: QueryDirectorPort,
    private val commandAccountPort: CommandAccountPort
): MappingDirectorUseCase {

    override fun execute(idx: Long) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx) ?: throw AccountNotFoundException()
        if (!queryDirectorPort.existsByIdx(idx)) {
            throw DirectorNotFoundException()
        }
        if (queryAccountPort.existsByDirector(idx)) {
            throw AlreadyMappingDirectorException()
        }
        val updateAccount = account.addDirector(idx)
        commandAccountPort.saveAccount(updateAccount)
    }

}