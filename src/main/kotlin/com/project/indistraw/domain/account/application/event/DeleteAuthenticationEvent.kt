package com.project.indistraw.domain.account.application.event

import com.project.indistraw.domain.account.domain.Authentication

data class DeleteAuthenticationEvent(
    val authentication: Authentication
)