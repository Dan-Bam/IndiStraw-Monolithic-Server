package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieHistoryRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieHistoryDetailResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieHistoryResponse
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieHistoryDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDetailDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDto
import org.springframework.stereotype.Component

@Component
class MovieHistoryDataMapper {

    fun toDto(request: CreateMovieHistoryRequest): CreateMovieHistoryDto =
        CreateMovieHistoryDto(
            idx = request.idx,
            historyTime = request.historyTime
        )

    fun toResponse(dto: MovieHistoryDto): MovieHistoryResponse =
        MovieHistoryResponse(
            title = dto.title,
            thumbnailUrl = dto.thumbnailUrl,
            historyTime = dto.historyTime,
            idx = dto.idx
        )

    fun toResponse(dto: MovieHistoryDetailDto): MovieHistoryDetailResponse =
        MovieHistoryDetailResponse(
            historyTime = dto.historyTime
        )

}