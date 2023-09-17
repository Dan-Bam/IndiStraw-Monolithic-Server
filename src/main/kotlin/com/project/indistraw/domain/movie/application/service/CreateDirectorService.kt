package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.movie.application.port.input.CreateDirectorUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.CreateDirectorDto
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto
import com.project.indistraw.domain.movie.application.port.output.CommandDirectorPort
import com.project.indistraw.domain.movie.domain.Director

@ServiceWithTransaction
class CreateDirectorService(
    private val commandDirectorPort: CommandDirectorPort
): CreateDirectorUseCase {

    override fun execute(createDirectorDto: CreateDirectorDto): DirectorDto {
        val director = commandDirectorPort.saveDirector(
            Director(
                id = 0,
                profileUrl = createDirectorDto.profileUrl,
                name = createDirectorDto.name
            )
        )

        return DirectorDto(
            id = director.id,
            name = director.name,
            profileUrl = director.profileUrl
        )
    }

}