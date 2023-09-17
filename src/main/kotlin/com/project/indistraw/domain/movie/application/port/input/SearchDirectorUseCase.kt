package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto

interface SearchDirectorUseCase {

    fun execute(name: String): List<DirectorDto>?

}