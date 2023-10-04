package com.project.indistraw.domain.movie.application.port.input.dto

data class MovieHistoryDto(
    val title: String,
    val thumbnailUrl: String,
    val historyTime: Float,
    val movieId: Int
)