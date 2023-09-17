package com.project.indistraw.domain.movie.adapter.input.mapper

import com.project.indistraw.domain.movie.adapter.input.data.response.DirectorResponse
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto
import org.springframework.stereotype.Component

@Component
class DirectorDataMapper {

    fun toResponse(dto: DirectorDto): DirectorResponse =
        DirectorResponse(
            id = dto.id,
            name = dto.name,
            profileUrl = dto.profileUrl
        )

}