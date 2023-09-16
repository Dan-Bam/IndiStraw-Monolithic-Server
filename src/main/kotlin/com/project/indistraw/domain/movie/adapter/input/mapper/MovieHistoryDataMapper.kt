package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieHistoryRequest
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieHistoryDto
import org.springframework.stereotype.Component

@Component
class MovieHistoryDataMapper {

    fun toDto(request: CreateMovieHistoryRequest): CreateMovieHistoryDto =
        CreateMovieHistoryDto(
            movieId = request.movieId,
            historyTime = request.historyTime
        )

}