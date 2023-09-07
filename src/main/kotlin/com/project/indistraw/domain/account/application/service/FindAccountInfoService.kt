package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.input.FindAccountInfoUseCase
import com.project.indistraw.domain.account.application.port.input.dto.AccountInfoDto
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class FindAccountInfoService(
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): FindAccountInfoUseCase {

    override fun execute(): AccountInfoDto {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        return AccountInfoDto(
            accountIdx = account.accountIdx,
            id = account.id,
            name = account.name,
            phoneNumber = account.phoneNumber,
            zipcode = account.address?.zipcode,
            address = account.address?.let {
                if (it.zipcode.isNullOrBlank()) null
                else it.streetAddress + " " + it.detailAddress
            },
            profileUrl = account.profileUrl
        )
    }

}