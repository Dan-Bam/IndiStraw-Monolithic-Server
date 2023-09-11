package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.AuthCode

interface QueryAuthCodePort {

    fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode?

}