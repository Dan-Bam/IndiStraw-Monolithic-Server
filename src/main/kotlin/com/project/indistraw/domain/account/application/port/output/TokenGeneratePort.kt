package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.application.port.output.dto.TokenDto
import com.project.indistraw.domain.account.domain.Authority
import java.util.*

interface TokenGeneratePort {

    fun generateToken(accountIdx: UUID, authority: Authority): TokenDto

}