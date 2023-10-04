package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.movie.application.port.input.CreateActorUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.input.dto.CreateActorDto
import com.project.indistraw.domain.movie.application.port.output.CommandActorPort
import com.project.indistraw.domain.movie.domain.Actor

@ServiceWithTransaction
class CreateActorService(
    private val commandActorPort: CommandActorPort
): CreateActorUseCase {

    override fun execute(createActorDto: CreateActorDto): ActorDto {
        val actor = commandActorPort.saveActor(
            Actor(
                idx = 0L,
                profileUrl = createActorDto.profileUrl,
                name = createActorDto.name
            )
        )

        return ActorDto(
            idx = actor.idx,
            profileUrl = actor.profileUrl,
            name = actor.name
        )
    }

}