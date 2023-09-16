package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto

interface SearchActorUseCase {

    fun execute(name: String): List<ActorDto>?

}