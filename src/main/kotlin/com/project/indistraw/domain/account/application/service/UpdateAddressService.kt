package com.project.indistraw.domain.account.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.input.UpdateAddressUseCase
import com.project.indistraw.domain.account.application.port.input.dto.UpdateAddressDto
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.CommandAccountPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.account.domain.Address

@ServiceWithTransaction
class UpdateAddressService(
    private val accountSecurityPort: AccountSecurityPort,
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort
): UpdateAddressUseCase {

    override fun execute(dto: UpdateAddressDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val address = Address(
            zipcode = dto.zipcode,
            streetAddress = dto.streetAddress,
            detailAddress = dto.detailAddress
        )

        commandAccountPort.saveAccount(
            account.updateAddress(address)
        )
    }
}