package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.exception.DuplicatedAccountIdException
import com.project.indistraw.domain.account.application.port.input.CheckAccountIdUseCase
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class CheckAccountIdService(
    private val queryAccountPort: QueryAccountPort
): CheckAccountIdUseCase {

    override fun execute(id: String) {
        if (queryAccountPort.existsById(id)) {
            throw DuplicatedAccountIdException()
        }
    }

}