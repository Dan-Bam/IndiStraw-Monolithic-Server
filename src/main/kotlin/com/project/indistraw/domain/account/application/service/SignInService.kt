package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.exception.PasswordNotMatchException
import com.project.indistraw.domain.account.application.port.input.SignInUseCase
import com.project.indistraw.domain.account.application.port.input.dto.SignInDto
import com.project.indistraw.domain.account.application.port.output.PasswordEncodePort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.account.application.port.output.TokenGeneratePort
import com.project.indistraw.domain.account.application.port.output.dto.TokenDto
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.account.application.event.SaveRefreshTokenEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class SignInService(
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val tokenGeneratePort: TokenGeneratePort,
    private val publisher: ApplicationEventPublisher
): SignInUseCase {

    override fun execute(dto: SignInDto): TokenDto {
        val account: Account = queryAccountPort.findByIdOrNull(dto.id)
            ?: throw AccountNotFoundException()

        if (!passwordEncodePort.isPasswordMatch(dto.password, account.encodedPassword)) {
            throw PasswordNotMatchException()
        }

        val token = tokenGeneratePort.generateToken(account.accountIdx, account.authority)

        // refreshToken을 redis에 저장하기 위한 event를 publish합니다.
        publishSaveRefreshToken(token, account)

        return token
    }

    private fun publishSaveRefreshToken(token: TokenDto, account: Account) {
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(
            refreshToken = token.refreshToken,
            accountIdx = account.accountIdx,
            expiredAt = token.refreshTokenExpiredAt
        )
        publisher.publishEvent(saveRefreshTokenEvent)
    }

}