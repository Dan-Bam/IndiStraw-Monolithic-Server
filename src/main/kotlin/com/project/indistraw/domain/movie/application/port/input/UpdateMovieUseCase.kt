package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.UpdateMovieDto

interface UpdateMovieUseCase {

    fun execute(id: Int, updateMovieDto: UpdateMovieDto)

}