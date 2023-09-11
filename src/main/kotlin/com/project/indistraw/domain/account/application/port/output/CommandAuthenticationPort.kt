package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.Authentication

interface CommandAuthenticationPort {

    fun saveAuthentication(authentication: Authentication)
    fun deleteAuthentication(authentication: Authentication)

}