package com.project.indistraw.domain.account.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.AuthenticationMapper
import com.project.indistraw.domain.account.adapter.output.persistence.repository.AuthenticationRepository
import com.project.indistraw.domain.account.application.port.output.CommandAuthenticationPort
import com.project.indistraw.domain.account.domain.Authentication
import org.springframework.stereotype.Component

@Component
class CommandAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationMapper: AuthenticationMapper
): CommandAuthenticationPort {

    override fun saveAuthentication(authentication: Authentication) {
        val authenticationEntity = authenticationMapper.toEntity(authentication)
        authenticationRepository.save(authenticationEntity)
    }

    override fun deleteAuthentication(authentication: Authentication) {
        val authenticationEntity = authenticationMapper.toEntity(authentication)
        authenticationRepository.delete(authenticationEntity)
    }

}