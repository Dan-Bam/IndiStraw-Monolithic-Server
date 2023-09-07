package com.project.indistraw.domain.account.application.event

import com.project.indistraw.domain.account.domain.Authentication

data class CreateAuthenticationEvent(
    val authentication: Authentication
)