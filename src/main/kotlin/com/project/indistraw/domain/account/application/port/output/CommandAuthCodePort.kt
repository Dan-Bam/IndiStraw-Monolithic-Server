package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.AuthCode

interface CommandAuthCodePort {

    fun saveAuthCode(authCode: AuthCode): Int

}