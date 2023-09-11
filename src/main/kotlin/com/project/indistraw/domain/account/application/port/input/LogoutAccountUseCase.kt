package com.project.indistraw.domain.account.application.port.input

interface LogoutAccountUseCase {

    fun execute(refreshToken: String)

}