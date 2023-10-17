package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.FilmographyDto

interface MyFilmographyUseCase {

    fun execute(): List<FilmographyDto>

}