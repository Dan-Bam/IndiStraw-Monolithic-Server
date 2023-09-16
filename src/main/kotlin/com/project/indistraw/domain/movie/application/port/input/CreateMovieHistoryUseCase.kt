package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieHistoryDto

interface CreateMovieHistoryUseCase {

    fun execute(createMovieHistoryDto: CreateMovieHistoryDto)

}