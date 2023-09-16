package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.response.ActorResponse
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import org.springframework.stereotype.Component

@Component
class ActorDataMapper {

    fun toResponse(dto: ActorDto): ActorResponse =
        ActorResponse(
            id = dto.id,
            name = dto.name,
            profileUrl = dto.profileUrl
        )

}