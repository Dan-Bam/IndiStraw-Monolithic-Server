package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.ActorIdDto

interface SearchActorIdUseCase {

    fun execute(id: Int): ActorIdDto

}