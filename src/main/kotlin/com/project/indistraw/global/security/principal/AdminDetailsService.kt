package com.project.indistraw.global.security.principal

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

@ServiceWithTransaction
class AdminDetailsService(
    private val queryAccountPort: QueryAccountPort
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        queryAccountPort.findByIdxOrNull(UUID.fromString(username))
            .let { it ?: throw AccountNotFoundException() }
            .let { AdminDetails(it.accountIdx) }

}