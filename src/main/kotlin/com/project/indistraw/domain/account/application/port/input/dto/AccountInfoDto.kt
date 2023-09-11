package com.project.indistraw.domain.account.application.port.input.dto

import java.util.*

data class AccountInfoDto(
    val accountIdx: UUID,
    val id: String,
    val name: String,
    val phoneNumber: String,
    val zipcode: String?,
    val address: String?,
    val profileUrl: String?
)