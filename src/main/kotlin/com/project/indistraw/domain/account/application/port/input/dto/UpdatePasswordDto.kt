package com.project.indistraw.domain.account.application.port.input.dto

data class UpdatePasswordDto(
    val phoneNumber: String,
    val newPassword: String
)