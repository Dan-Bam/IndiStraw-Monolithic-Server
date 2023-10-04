package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.MovieDetailDto

interface MovieDetailUseCase {

    fun execute(idx: Long): MovieDetailDto

}