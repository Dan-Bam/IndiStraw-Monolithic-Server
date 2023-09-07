package com.project.indistraw.domain.account.adapter.output.message

import com.project.indistraw.domain.account.adapter.output.message.exception.MessageSendFailedException
import com.project.indistraw.domain.account.adapter.output.message.properties.CoolSmsProperties
import com.project.indistraw.domain.account.application.port.output.SendMessagePort
import mu.KotlinLogging
import net.nurigo.java_sdk.api.Message
import net.nurigo.java_sdk.exceptions.CoolsmsException
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CoolSmsAdapter(
    private val coolSmsProperties: CoolSmsProperties
): SendMessagePort {

    override fun sendMessage(phoneNumber: String, authCode: Int) {
        val coolsms = Message(coolSmsProperties.access, coolSmsProperties.secret)

        val params: HashMap<String, String> = HashMap()
        params["to"] = phoneNumber
        params["from"] = coolSmsProperties.phoneNumber
        params["type"] = "SMS"
        params["text"] = "IndiStraw 인증번호는 [ $authCode ] 입니다."
        params["app_version"] = "test app 1.2"

        try {
            coolsms.send(params)
        } catch (e: CoolsmsException) {
            e.printStackTrace()
            log.error("coolsms message send failed")
            throw MessageSendFailedException()
        }
    }

}