package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.FilmographyDto

interface MyFilmographyUseCase {

    fun execute(): List<FilmographyDto>

}