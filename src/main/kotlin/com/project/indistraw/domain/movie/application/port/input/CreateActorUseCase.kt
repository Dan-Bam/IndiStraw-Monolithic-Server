package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.input.dto.CreateActorDto

interface CreateActorUseCase {

    fun execute(createActorDto: CreateActorDto): ActorDto

}