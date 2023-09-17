package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.CreateDirectorDto
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto

interface CreateDirectorUseCase {

    fun execute(createDirectorDto: CreateDirectorDto): DirectorDto

}