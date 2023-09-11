package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.SignInDto
import com.project.indistraw.domain.account.application.port.output.dto.TokenDto

interface SignInUseCase {

    fun execute(dto: SignInDto): TokenDto

}