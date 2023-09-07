package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.RefreshToken

interface CommandRefreshTokenPort {

    fun saveRefreshToken(refreshToken: RefreshToken): String
    fun deleteRefreshToken(refreshToken: RefreshToken)

}