package com.project.indistraw.domain.account.adapter.input.data.request

import javax.validation.constraints.NotNull

data class UpdateAddressRequest(
    @field:NotNull
    val zipcode: String,

    @field:NotNull
    val streetAddress: String,

    @field:NotNull
    val detailAddress: String
)
