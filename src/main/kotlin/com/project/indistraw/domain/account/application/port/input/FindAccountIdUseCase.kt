package com.project.indistraw.domain.account.application.port.input

interface FindAccountIdUseCase {

    fun execute(phoneNumber: String): String

}