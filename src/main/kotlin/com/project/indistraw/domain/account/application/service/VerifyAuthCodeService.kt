package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AuthCodeNotFoundException
import com.project.indistraw.domain.account.application.exception.AuthCodeNotMatchException
import com.project.indistraw.domain.account.application.exception.AuthenticationNotFoundException
import com.project.indistraw.domain.account.application.exception.TooManyAuthCodeRequestException
import com.project.indistraw.domain.account.application.port.input.VerifyAuthCodeUseCase
import com.project.indistraw.domain.account.application.port.output.QueryAuthCodePort
import com.project.indistraw.domain.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.domain.account.application.event.CreateAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class VerifyAuthCodeService(
    private val queryAuthCodePort: QueryAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher,
): VerifyAuthCodeUseCase {

    override fun execute(authCode: Int, phoneNumber: String) {
        val authCodeDomain = queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthCodeNotFoundException()
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException()

        if (authentication.authCodeCount > 5) throw TooManyAuthCodeRequestException()

        if (authCodeDomain.authCode != authCode) {
            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseAuthCodeCount()))
            throw AuthCodeNotMatchException()
        }

        publisher.publishEvent(CreateAuthenticationEvent(authentication.certified()))
    }

}