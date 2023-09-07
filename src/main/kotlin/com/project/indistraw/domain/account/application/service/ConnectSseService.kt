package com.project.indistraw.domain.account.application.service

import com.project.indistraw.domain.account.application.exception.InvalidQRCodeUUIDException
import com.project.indistraw.domain.account.application.port.input.ConnectSseUseCase
import com.project.indistraw.domain.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.domain.account.application.port.output.SseEmitterPort
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*

@Service
class ConnectSseService(
    private val sseEmitterPort: SseEmitterPort,
    private val queryQRCodeUUIDPort: QueryQRCodeUUIDPort
): ConnectSseUseCase {

    override fun execute(uuid: UUID): SseEmitter {
        if (!queryQRCodeUUIDPort.existByUUID(uuid)) {
            throw InvalidQRCodeUUIDException()
        }
        // 생성된 uuid를 key로 redis sse 채널을 생성한다.
        return sseEmitterPort.createSseChanel(uuid)
    }

}