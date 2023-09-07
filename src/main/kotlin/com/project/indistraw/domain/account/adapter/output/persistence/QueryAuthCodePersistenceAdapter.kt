package com.project.indistraw.domain.account.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.AuthCodeMapper
import com.project.indistraw.domain.account.adapter.output.persistence.repository.AuthCodeRepository
import com.project.indistraw.domain.account.application.port.output.QueryAuthCodePort
import com.project.indistraw.domain.account.domain.AuthCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryAuthCodePersistenceAdapter(
    private val authCodeRepository: AuthCodeRepository,
    private val authCodeMapper: AuthCodeMapper
): QueryAuthCodePort {

    override fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode? {
        val authCodeEntity = authCodeRepository.findByIdOrNull(phoneNumber)
        return authCodeMapper toDomain authCodeEntity
    }

}