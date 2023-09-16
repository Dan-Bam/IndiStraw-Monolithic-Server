package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieHistoryRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieHistoryResponse
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieHistoryDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDto
import org.springframework.stereotype.Component

@Component
class MovieHistoryDataMapper {

    fun toDto(request: CreateMovieHistoryRequest): CreateMovieHistoryDto =
        CreateMovieHistoryDto(
            movieId = request.movieId,
            historyTime = request.historyTime
        )

    fun toResponse(dto: MovieHistoryDto): MovieHistoryResponse =
        MovieHistoryResponse(
            title = dto.title,
            thumbnailUrl = dto.thumbnailUrl,
            historyTime = dto.historyTime,
            movieIdx = dto.movieIdx
        )

}