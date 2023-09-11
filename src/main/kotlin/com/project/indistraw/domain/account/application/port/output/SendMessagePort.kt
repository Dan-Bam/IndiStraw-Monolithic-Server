package com.project.indistraw.domain.account.application.port.output

interface SendMessagePort {

    fun sendMessage(phoneNumber: String, authCode: Int)

}