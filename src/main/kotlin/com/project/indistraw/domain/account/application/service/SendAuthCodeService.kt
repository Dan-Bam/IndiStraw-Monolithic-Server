package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.TooManyAuthenticationRequestException
import com.project.indistraw.domain.account.application.port.input.SendAuthCodeUseCase
import com.project.indistraw.domain.account.application.port.output.CommandAuthCodePort
import com.project.indistraw.domain.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.domain.account.application.port.output.SendMessagePort
import com.project.indistraw.domain.account.domain.AuthCode
import com.project.indistraw.domain.account.domain.Authentication
import com.project.indistraw.domain.account.application.event.CreateAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ServiceWithTransaction
class SendAuthCodeService(
    private val sendMessagePort: SendMessagePort,
    private val commandAuthCodePort: CommandAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher
): SendAuthCodeUseCase {

    override fun execute(phoneNumber: String) {
        val isExistsAuthentication = queryAuthenticationPort.existsByPhoneNumber(phoneNumber)

        // 이미 authentication을 요청한 사용자가 5번 요청을 초과 할 시 예외처리 한다.
        if (isExistsAuthentication) {
            val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            if (authentication!!.authenticationCount > 5) {
                throw TooManyAuthenticationRequestException()
            }
            // 5번을 초과 하지 않았다면 attemptCount를 1 증가 시킨다.
            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseAuthenticationCount()))
        }

        val code = createCode()
        // 요청받은 핸드폰 번호로 문자를 발송한다.
        sendMessagePort.sendMessage(phoneNumber, code)
        val authCode = AuthCode(
            phoneNumber = phoneNumber,
            authCode = code,
            expiredAt = 300
        )
        commandAuthCodePort.saveAuthCode(authCode)

        // authentication이 없는 사용자는 authentication을 생성한다.
        if (!isExistsAuthentication) {
            val authentication = Authentication(
                phoneNumber = phoneNumber,
                authCodeCount = 0,
                authenticationCount = 0,
                isVerified = false,
                expiredAt = Authentication.EXPIRED_AT
            )
            publisher.publishEvent(CreateAuthenticationEvent(authentication))
        }
    }

    private fun createCode() = Random().nextInt(8888) + 1111

}