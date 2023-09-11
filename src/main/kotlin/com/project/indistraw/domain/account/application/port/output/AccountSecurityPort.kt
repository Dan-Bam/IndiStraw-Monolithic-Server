package com.project.indistraw.domain.account.application.port.output

import java.util.*

interface AccountSecurityPort {

    fun getCurrentAccountIdx(): UUID

}