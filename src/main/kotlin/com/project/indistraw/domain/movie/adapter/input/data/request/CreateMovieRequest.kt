package com.project.indistraw.domain.movie.adapter.input.data.request

import javax.validation.constraints.NotNull

data class CreateMovieRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val description: String,
    @field:NotNull
    val movieUrl: String,
    @field:NotNull
    val thumbnailUrl: String,
    @field:NotNull
    val movieHighlight: List<String>,
    @field:NotNull
    val director: List<Long>,
    @field:NotNull
    val actor: List<Long>,
    @field:NotNull
    val crowdTrue: Boolean
)