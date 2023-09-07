package com.project.indistraw.domain.account.application.port.input

interface SendAuthCodeUseCase {

    fun execute(phoneNumber: String)

}