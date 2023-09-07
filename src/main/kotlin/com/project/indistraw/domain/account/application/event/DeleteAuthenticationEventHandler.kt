package com.project.indistraw.domain.account.application.event

import com.project.indistraw.domain.account.application.port.output.CommandAuthenticationPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class DeleteAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // event publisher가 commit된 후 해당 event handler가 실행된다.
    fun deleteAuthentication(deleteAuthenticationEvent: DeleteAuthenticationEvent) {
        log.info("deleteAuthenticationEvent is activate")

        commandAuthenticationPort.deleteAuthentication(deleteAuthenticationEvent.authentication)
    }

}