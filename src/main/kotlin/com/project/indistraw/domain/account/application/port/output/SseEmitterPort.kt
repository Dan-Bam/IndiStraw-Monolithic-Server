package com.project.indistraw.domain.account.application.port.output

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*

interface SseEmitterPort {

    fun createSseChanel(uuid: UUID): SseEmitter
    fun sendData(uuid: UUID, sendData: Any)

}