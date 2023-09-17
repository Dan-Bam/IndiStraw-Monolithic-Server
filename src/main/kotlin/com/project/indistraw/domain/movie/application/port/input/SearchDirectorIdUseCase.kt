package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.DirectorIdDto

interface SearchDirectorIdUseCase {

    fun execute(id: Int): DirectorIdDto

}