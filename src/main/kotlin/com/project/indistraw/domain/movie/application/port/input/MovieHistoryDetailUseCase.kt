package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDetailDto

interface MovieHistoryDetailUseCase {

    fun execute(idx: Long): MovieHistoryDetailDto

}