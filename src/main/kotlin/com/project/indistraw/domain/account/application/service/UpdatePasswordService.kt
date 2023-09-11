package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.common.util.AuthenticationValidator
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.exception.DuplicatedNewPasswordException
import com.project.indistraw.domain.account.application.port.input.UpdatePasswordUseCase
import com.project.indistraw.domain.account.application.port.input.dto.UpdatePasswordDto
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.PasswordEncodePort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.account.application.event.DeleteAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class UpdatePasswordService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): UpdatePasswordUseCase {

    override fun execute(dto: UpdatePasswordDto) {
        val account = queryAccountPort.findByPhoneNumberOrNull(dto.phoneNumber)
            ?: throw AccountNotFoundException()

        if (passwordEncodePort.isPasswordMatch(dto.newPassword, account.encodedPassword)) {
            throw DuplicatedNewPasswordException()
        }

        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(account.phoneNumber)
        publisher.publishEvent(DeleteAuthenticationEvent(authentication))

        val encodedNewPassword = passwordEncodePort.passwordEncode(dto.newPassword)
        commandAccountPort.saveAccount(
            account.updateEncodedPassword(encodedNewPassword)
        )
    }

}