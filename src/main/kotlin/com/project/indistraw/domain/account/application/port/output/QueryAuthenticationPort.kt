package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.Authentication

interface QueryAuthenticationPort {

    fun findByPhoneNumberOrNull(phoneNumber: String): Authentication?
    fun existsByPhoneNumber(phoneNumber: String): Boolean

}