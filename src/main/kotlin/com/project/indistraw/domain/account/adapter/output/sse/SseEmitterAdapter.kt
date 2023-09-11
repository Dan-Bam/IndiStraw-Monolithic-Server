package com.project.indistraw.domain.account.adapter.output.sse

import com.project.indistraw.domain.account.adapter.output.sse.exception.InvalidSseConnectionException
import com.project.indistraw.domain.account.application.port.output.SseEmitterPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

private val log = KotlinLogging.logger {  }

@Component
class SseEmitterAdapter: SseEmitterPort {

    private val clients: MutableMap<UUID, SseEmitter> = ConcurrentHashMap()

    companion object {
        const val DEFAULT_TIMEOUT = 60L * 1000 * 10
    }

    override fun createSseChanel(uuid: UUID): SseEmitter {
        // SSE 통신을 위한 SSE emitter 객체를 생성한다.
        val emitter = SseEmitter(TimeUnit.MINUTES.toMillis(DEFAULT_TIMEOUT))
        sendToClient(emitter, "$uuid", "CONNECT", "SUCCESS [ " + uuid + LocalDateTime.now() + " ]")
        clients[uuid] = emitter
        return emitter
    }

    override fun sendData(uuid: UUID, sendData: Any) {
        log.info("sendData {}", sendData)
        clients.forEach{ (uuid, emitter) ->
            log.info("sseEmitter {}", emitter)
            sendToClient(emitter, "$uuid", "SEND SUCCESS [ " + uuid + LocalDateTime.now() + " ]", sendData)
        }
        clients[uuid]?.complete()
        clients.remove(uuid)
    }

    private fun sendToClient(emitter: SseEmitter, id: String, name: String, data: Any) {
        runCatching {
            emitter.send(
                SseEmitter.event()
                    .id(id)
                    .name(name)
                    .data(data)
            )
        }.onFailure {
            it.printStackTrace()
            log.info("Connection end")
            throw InvalidSseConnectionException()
        }
    }

}