package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.application.exception.ActorNotFoundException
import com.project.indistraw.domain.movie.application.port.input.SearchActorIdUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.ActorIdDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort

@ServiceWithReadOnlyTransaction
class SearchActorIdService(
    private val queryActorPort: QueryActorPort,
    private val queryMoviePort: QueryMoviePort,
): SearchActorIdUseCase {

    override fun execute(id: Int): ActorIdDto {
        val actor = queryActorPort.findById(id) ?: throw ActorNotFoundException()
        val movieList = queryMoviePort.findByActorContaining(actor.id)
        val movieListDto = movieList.map{
            MovieDto(
                id = it.id,
                thumbnailUrl = it.thumbnailUrl
            )
        }.toList()

        return ActorIdDto(
            id = actor.id,
            name = actor.name,
            profileUrl = actor.profileUrl,
            movieList = movieListDto
        )
    }

}