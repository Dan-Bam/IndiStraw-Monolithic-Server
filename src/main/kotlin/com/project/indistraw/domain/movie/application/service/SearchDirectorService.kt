package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.application.port.input.SearchDirectorUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort

@ServiceWithReadOnlyTransaction
class SearchDirectorService(
    private val commandDirectorPort: QueryDirectorPort
): SearchDirectorUseCase {

    override fun execute(name: String): List<DirectorDto>? =
        commandDirectorPort.findByNameContaining(name)
            .map {
                DirectorDto(
                    id = it.id,
                    name = it.name,
                    profileUrl = it.profileUrl
                )
            }

}