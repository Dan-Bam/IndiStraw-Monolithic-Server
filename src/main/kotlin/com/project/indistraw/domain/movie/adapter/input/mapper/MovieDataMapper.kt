package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieDetailResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.MoviePagingResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieResponse
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDetailDto
import com.project.indistraw.domain.movie.application.port.input.dto.MoviePagingDto
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

    fun toResponse(dto: MoviePagingDto): MoviePagingResponse =
        MoviePagingResponse(
            isLate = dto.isLate,
            list = dto.list.map {
                MovieResponse(
                    idx = it.idx,
                    thumbnailUrl = it.thumbnailUrl
                )
            }
        )

    fun toResponse(dto: MovieDetailDto): MovieDetailResponse =
        MovieDetailResponse(
            title = dto.title,
            description = dto.description,
            movieUrl = dto.movieUrl,
            thumbnailUrl = dto.thumbnailUrl,
            director = dto.director,
            actor = dto.actor,
            movieHighLight = dto.movieHighLight,
            clowdTrue = dto.clowdTrue,
            genre = dto.genre
        )

}