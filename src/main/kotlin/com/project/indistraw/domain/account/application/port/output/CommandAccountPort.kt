package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.Account
import java.util.*

interface CommandAccountPort {

    fun saveAccount(account: Account): UUID
    fun deleteAccount(account: Account)

}