package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.UpdatePasswordDto

interface UpdatePasswordUseCase {

    fun execute(dto: UpdatePasswordDto)

}