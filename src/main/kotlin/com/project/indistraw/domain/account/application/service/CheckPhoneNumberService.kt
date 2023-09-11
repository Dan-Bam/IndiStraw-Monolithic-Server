package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.common.enums.CheckPhoneNumberType
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.exception.DuplicatedPhoneNumberException
import com.project.indistraw.domain.account.application.port.input.CheckPhoneNumberUseCase
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class CheckPhoneNumberService(
    private val queryAccountPort: QueryAccountPort
): CheckPhoneNumberUseCase {

    override fun execute(phoneNumber: String, type: CheckPhoneNumberType) {
        when(type) {
            CheckPhoneNumberType.SIGNUP, CheckPhoneNumberType.CHANGE_PHONE -> {
                if (queryAccountPort.existsByPhoneNumber(phoneNumber)) {
                    throw DuplicatedPhoneNumberException()
                }
            }
            CheckPhoneNumberType.FIND_ACCOUNT -> {
                if (!queryAccountPort.existsByPhoneNumber(phoneNumber)) {
                    throw AccountNotFoundException()
                }
            }
        }
    }
}