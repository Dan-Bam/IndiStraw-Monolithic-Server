package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.port.input.LogoutAccountUseCase
import com.project.indistraw.domain.account.application.port.output.CommandRefreshTokenPort
import com.project.indistraw.domain.account.application.port.output.QueryRefreshTokenPort
import com.project.indistraw.domain.account.application.port.output.TokenParsePort
import com.project.indistraw.global.security.token.common.exception.ExpiredRefreshTokenException
import com.project.indistraw.global.security.token.common.exception.InvalidTokenTypeException

@ServiceWithTransaction
class LogoutAccountService(
    private val commandRefreshTokenPort: CommandRefreshTokenPort,
    private val tokenParsePort: TokenParsePort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
): LogoutAccountUseCase {

    override fun execute(refreshToken: String) {
        val parsedRefreshToken = tokenParsePort.parseRefreshTokenToken(refreshToken)
            ?: throw InvalidTokenTypeException()

        val refreshTokenDomain = queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken)
            ?: throw ExpiredRefreshTokenException()

        commandRefreshTokenPort.deleteRefreshToken(refreshTokenDomain)
    }

}