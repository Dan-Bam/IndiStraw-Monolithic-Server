package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieDto

interface CreateMovieUseCase {

    fun execute(createMovieDto: CreateMovieDto)

}