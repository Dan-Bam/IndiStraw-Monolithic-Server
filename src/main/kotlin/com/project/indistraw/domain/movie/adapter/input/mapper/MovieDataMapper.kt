package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieResponse
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto
import org.springframework.stereotype.Component

@Component
class MovieDataMapper {

    fun toDto(request: CreateMovieRequest): CreateMovieDto =
        CreateMovieDto(
            title = request.title,
            description = request.description,
            movieUrl = request.movieUrl,
            thumbnailUrl = request.thumbnailUrl,
            movieHighlight = request.movieHighlight,
            director = request.director,
            actor = request.actor,
            clowdTrue = request.clowdTrue
        )

    fun toResponse(dto: MovieDto): MovieResponse =
        MovieResponse(
            idx = dto.idx,
            thumbnailUrl = dto.thumbnailUrl
        )

}