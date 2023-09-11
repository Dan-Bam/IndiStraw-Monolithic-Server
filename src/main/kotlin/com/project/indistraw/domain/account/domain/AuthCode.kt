package com.project.indistraw.domain.account.domain

data class AuthCode(
    val phoneNumber: String,
    val authCode: Int,
    val expiredAt: Long
)
