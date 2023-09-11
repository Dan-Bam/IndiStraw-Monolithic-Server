package com.project.indistraw.domain.account.application.port.input

interface VerifyAuthCodeUseCase {

    fun execute(authCode: Int, phoneNumber: String)

}