package com.project.indistraw.domain.movie.adapter.input.data.response

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto

data class MovieDetailResponse(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val director: List<DirectorDto>,
    val actor: List<ActorDto>,
    val movieHighlight: List<String>,
    val crowdTrue: Boolean,
    val genre: Genre
)