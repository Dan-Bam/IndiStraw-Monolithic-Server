package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.AccountInfoDto

interface FindAccountInfoUseCase {

    fun execute(): AccountInfoDto

}