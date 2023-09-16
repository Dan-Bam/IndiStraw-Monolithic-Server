package com.project.indistraw.domain.movie.application.port.input.dto

data class CreateMovieHistoryDto(
    val movieId: Int,
    val historyTime: Float
)