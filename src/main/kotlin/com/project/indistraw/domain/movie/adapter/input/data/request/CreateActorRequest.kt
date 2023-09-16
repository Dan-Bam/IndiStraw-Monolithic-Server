package com.project.indistraw.domain.movie.adapter.input.data.request

import javax.validation.constraints.NotNull

data class CreateActorRequest(
    @field:NotNull
    val name: String,
    @field:NotNull
    val profileUrl: String
)