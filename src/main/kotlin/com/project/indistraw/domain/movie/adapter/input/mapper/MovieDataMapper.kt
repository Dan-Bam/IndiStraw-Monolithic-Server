package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieDto
import org.springframework.stereotype.Component

@Component
class MovieDataMapper {

    fun toDto(createMovieRequest: CreateMovieRequest): CreateMovieDto =
        CreateMovieDto(
            title = createMovieRequest.title,
            description = createMovieRequest.description,
            movieUrl = createMovieRequest.movieUrl,
            thumbnailUrl = createMovieRequest.thumbnailUrl,
            movieHighlight = createMovieRequest.movieHighlight,
            director = createMovieRequest.director,
            actor = createMovieRequest.actor,
            clowdTrue = createMovieRequest.clowdTrue
        )

}