package com.project.indistraw.domain.account.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.RefreshTokenMapper
import com.project.indistraw.domain.account.adapter.output.persistence.repository.RefreshTokenRepository
import com.project.indistraw.domain.account.application.port.output.CommandRefreshTokenPort
import com.project.indistraw.domain.account.domain.RefreshToken
import org.springframework.stereotype.Component

@Component
class CommandRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
): CommandRefreshTokenPort {

    override fun saveRefreshToken(refreshToken: RefreshToken): String {
        val refreshTokenEntity = refreshTokenMapper toEntity refreshToken
        return refreshTokenRepository.save(refreshTokenEntity).refreshToken
    }

    override fun deleteRefreshToken(refreshToken: RefreshToken) {
        val refreshTokenEntity = refreshTokenMapper toEntity refreshToken
        return refreshTokenRepository.delete(refreshTokenEntity)
    }

}