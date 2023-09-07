package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.common.util.AuthenticationValidator
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.input.FindAccountIdUseCase
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.account.application.event.DeleteAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithReadOnlyTransaction
class FindAccountIdService(
    private val queryAccountPort: QueryAccountPort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): FindAccountIdUseCase {

    override fun execute(phoneNumber: String): String {
        val account = queryAccountPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AccountNotFoundException()
        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(phoneNumber)
        publisher.publishEvent(DeleteAuthenticationEvent(authentication))
        return account.id
    }

}