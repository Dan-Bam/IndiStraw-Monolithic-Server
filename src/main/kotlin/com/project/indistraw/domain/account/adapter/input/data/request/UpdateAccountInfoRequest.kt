package com.project.indistraw.domain.account.adapter.input.data.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateAccountInfoRequest(
    @field:NotNull
    @field:Size(min = 2, max = 10)
    val name: String,

    val profileUrl: String?
)