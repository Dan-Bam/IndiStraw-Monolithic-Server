package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.request.UpdateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieDetailResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.MoviePagingResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieResponse
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDetailDto
import com.project.indistraw.domain.movie.application.port.input.dto.MoviePagingDto
import com.project.indistraw.domain.movie.application.port.input.dto.UpdateMovieDto
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
            crowdTrue = request.crowdTrue
        )

    fun toDto(request: UpdateMovieRequest): UpdateMovieDto =
        UpdateMovieDto(
            title = request.title,
            description = request.description,
            movieUrl = request.movieUrl,
            thumbnailUrl = request.thumbnailUrl,
            movieHighlight = request.movieHighlight,
            director = request.director,
            actor = request.actor,
            crowdTrue = request.crowdTrue
        )

    fun toResponse(dto: MoviePagingDto): MoviePagingResponse =
        MoviePagingResponse(
            isLast = dto.isLast,
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
            movieHighlight = dto.movieHighLight,
            crowdTrue = dto.crowdTrue,
            genre = dto.genre
        )

}