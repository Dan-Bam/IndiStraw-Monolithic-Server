package com.project.indistraw.domain.funding.adapter.input.data.request

import javax.validation.constraints.NotNull

data class SaveFundingRequest(
    @field:NotNull
    val receiptId: String,

    @field:NotNull
    val price: Long,

    val extraPrice: Long?
)