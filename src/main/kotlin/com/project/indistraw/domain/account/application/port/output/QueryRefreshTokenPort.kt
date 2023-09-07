package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.RefreshToken

interface QueryRefreshTokenPort {

    fun findByRefreshTokenOrNull(refreshToken: String): RefreshToken?

}