package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDto

interface MovieHistoryListUseCase {

    fun execute(): List<MovieHistoryDto>

}