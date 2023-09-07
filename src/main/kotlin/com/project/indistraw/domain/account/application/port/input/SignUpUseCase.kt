package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.SignUpDto

interface SignUpUseCase {

    fun execute(dto: SignUpDto)

}