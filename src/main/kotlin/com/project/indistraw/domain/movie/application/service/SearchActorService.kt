package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.movie.application.port.input.SearchActorUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort

@ServiceWithTransaction
class SearchActorService(
    private val queryActorPort: QueryActorPort
): SearchActorUseCase{

    override fun execute(name: String): List<ActorDto>? =
        queryActorPort.findByNameContaining(name)
            .map {
                ActorDto(
                    id = it.id,
                    name = it.name,
                    profileUrl = it.profileUrl
                )
            }

}