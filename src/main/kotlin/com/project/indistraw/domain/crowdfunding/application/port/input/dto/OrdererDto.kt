package com.project.indistraw.domain.crowdfunding.application.port.input.dto

import java.util.*

data class OrdererDto(
    val accountIdx: UUID,
    val name: String,
    val phoneNumber: String,
    val zipcode: String?,
    val address: String?,
    val profileUrl: String?,
)