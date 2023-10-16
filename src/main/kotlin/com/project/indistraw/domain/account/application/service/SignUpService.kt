package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.common.util.AuthenticationValidator
import com.project.indistraw.domain.account.application.exception.DuplicatedAccountIdException
import com.project.indistraw.domain.account.application.port.input.SignUpUseCase
import com.project.indistraw.domain.account.application.port.input.dto.SignUpDto
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.PasswordEncodePort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.account.domain.Address
import com.project.indistraw.domain.account.domain.Authority
import com.project.indistraw.domain.account.application.event.DeleteAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ServiceWithTransaction
class SignUpService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): SignUpUseCase {

    override fun execute(dto: SignUpDto) {
        if (queryAccountPort.existsById(dto.id)) throw DuplicatedAccountIdException()

        // 인증된 사용자라면 확인 후, authentication 삭제 이벤트를 발행합니다.
        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(dto.phoneNumber)
        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)
        publisher.publishEvent(deleteAuthenticationEvent)

        val account = dto.let {
            Account(
                accountIdx = UUID.randomUUID(),
                id = it.id,
                encodedPassword = passwordEncodePort.passwordEncode(dto.password),
                name = it.name,
                phoneNumber = it.phoneNumber,
                address = Address(null, null, null),
                profileUrl = it.profileUrl,
                authority = Authority.ROLE_ACCOUNT,
                actor = null,
                director = null
            )
        }
        commandAccountPort.saveAccount(account)
    }

}