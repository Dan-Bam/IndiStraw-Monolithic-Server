package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateActorRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.ActorResponse
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.input.dto.CreateActorDto
import org.springframework.stereotype.Component

@Component
class ActorDataMapper {

    fun toResponse(dto: ActorDto): ActorResponse =
        ActorResponse(
            id = dto.id,
            name = dto.name,
            profileUrl = dto.profileUrl
        )

    fun toDto(createActorRequest: CreateActorRequest): CreateActorDto =
        CreateActorDto(
            name = createActorRequest.name,
            profileUrl = createActorRequest.profileUrl
        )

}