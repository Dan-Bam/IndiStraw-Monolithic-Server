package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateActorRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.ActorIdResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.ActorResponse
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.input.dto.ActorIdDto
import com.project.indistraw.domain.movie.application.port.input.dto.CreateActorDto
import org.springframework.stereotype.Component

@Component
class ActorDataMapper {

    fun toResponse(dto: ActorDto): ActorResponse =
        ActorResponse(
            idx = dto.idx,
            name = dto.name,
            profileUrl = dto.profileUrl
        )

    fun toResponse(dto: ActorIdDto): ActorIdResponse =
        ActorIdResponse(
            idx = dto.idx,
            name = dto.name,
            profileUrl = dto.profileUrl,
            movieList = dto.movieList
        )

    fun toDto(request: CreateActorRequest): CreateActorDto =
        CreateActorDto(
            name = request.name,
            profileUrl = request.profileUrl
        )

}