package com.project.indistraw.domain.movie.adapter.input.data.request

import javax.validation.constraints.NotNull

data class CreateMovieHistoryRequest(
    @field:NotNull
    val movieIdx: Long,
    @field:NotNull
    val historyTime: Float
)