package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.UpdateAccountInfoDto

interface UpdateAccountInfoUseCase {

    fun execute(dto: UpdateAccountInfoDto)

}