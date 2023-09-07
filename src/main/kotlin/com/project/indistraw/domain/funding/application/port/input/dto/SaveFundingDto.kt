package com.project.indistraw.domain.funding.application.port.input.dto

data class SaveFundingDto(
    val crowdfundingIdx: Long,
    val rewordIdx: Long,
    val receiptId: String,
    val price: Long,
    val extraPrice: Long?
)