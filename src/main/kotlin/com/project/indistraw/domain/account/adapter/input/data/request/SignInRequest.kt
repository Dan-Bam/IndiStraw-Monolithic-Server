package com.project.indistraw.domain.account.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignInRequest(
    @field:NotNull
    @field:Length(min = 6, max = 15)
    val id: String,

    @field:NotNull
    @field:Length(min = 8, max = 20)
    @field:Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*?~])[0-9a-zA-Z!@#$%^&*?~]+$")
    val password: String,
)
