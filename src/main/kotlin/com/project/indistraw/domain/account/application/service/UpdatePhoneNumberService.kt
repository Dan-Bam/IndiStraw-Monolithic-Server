package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.common.util.AuthenticationValidator
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.account.application.port.input.UpdatePhoneNumberUseCase
import com.project.indistraw.domain.account.application.event.DeleteAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class UpdatePhoneNumberService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val authenticationValidator: AuthenticationValidator,
    private val accountSecurityPort: AccountSecurityPort,
    private val publisher: ApplicationEventPublisher
): UpdatePhoneNumberUseCase {

    override fun execute(phoneNumber: String) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(phoneNumber)
        publisher.publishEvent(DeleteAuthenticationEvent(authentication))

        commandAccountPort.saveAccount(
            account.updatePhoneNumber(phoneNumber = phoneNumber)
        )
    }

}