package com.project.indistraw.domain.account.application.port.output

import com.project.indistraw.domain.account.domain.Account
import java.util.*

interface QueryAccountPort {

    fun existsById(id: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findByIdOrNull(id: String): Account?
    fun findByIdxOrNull(idx: UUID): Account?
    fun findByPhoneNumberOrNull(phoneNumber: String): Account?
    fun existsByActor(actorIdx: Long): Boolean
    fun existsByDirector(directorIdx: Long): Boolean

}