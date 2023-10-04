package com.project.indistraw.domain.movie.application.port.input.dto

data class CreateMovieHistoryDto(
    val movieIdx: Long,
    val historyTime: Float
)