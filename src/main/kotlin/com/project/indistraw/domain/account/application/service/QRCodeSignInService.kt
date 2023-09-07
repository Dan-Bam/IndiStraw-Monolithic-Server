package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.exception.InvalidQRCodeUUIDException
import com.project.indistraw.domain.account.application.port.input.QRCodeSignInUseCase
import com.project.indistraw.domain.account.application.port.input.dto.QRCodeUUIDDto
import com.project.indistraw.domain.account.application.port.output.*
import com.project.indistraw.domain.account.application.port.output.dto.TokenDto
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.account.application.event.SaveRefreshTokenEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithReadOnlyTransaction
class QRCodeSignInService(
    private val queryQRCodeUUIDPort: QueryQRCodeUUIDPort,
    private val sseEmitterPort: SseEmitterPort,
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val tokenGeneratePort: TokenGeneratePort,
    private val publisher: ApplicationEventPublisher
): QRCodeSignInUseCase {

    override fun execute(dto: QRCodeUUIDDto) {
        if (!queryQRCodeUUIDPort.existByUUID(dto.uuid)) {
            throw InvalidQRCodeUUIDException()
        }
        val account = queryAccountPort.findByIdxOrNull(accountSecurityPort.getCurrentAccountIdx())
            ?: throw AccountNotFoundException()
        val token = tokenGeneratePort.generateToken(account.accountIdx, account.authority)
        publishSaveRefreshToken(token, account)
        // uuid, account가 유효한지 확인 한 후 sse로 token을 보낸다.
        sseEmitterPort.sendData(uuid = dto.uuid, token.toString())
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