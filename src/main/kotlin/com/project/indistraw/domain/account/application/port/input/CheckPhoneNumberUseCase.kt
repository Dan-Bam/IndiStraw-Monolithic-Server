package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.common.enums.CheckPhoneNumberType

interface CheckPhoneNumberUseCase {

    fun execute(phoneNumber: String, type: CheckPhoneNumberType)

}