package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.application.exception.DirectorNotFoundException
import com.project.indistraw.domain.movie.application.port.input.SearchDirectorIdUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorIdDto
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort

@ServiceWithReadOnlyTransaction
class SearchDirectorIdService(
    private val queryDirectorPort: QueryDirectorPort,
    private val queryMoviePort: QueryMoviePort
): SearchDirectorIdUseCase {

    override fun execute(idx: Long): DirectorIdDto {
        val director = queryDirectorPort.findByIdOrNull(idx) ?: throw DirectorNotFoundException()
        val movieList = queryMoviePort.findByActorContaining(director.idx)
        val movieListDto = movieList.map{
            MovieDto(
                idx = it.idx,
                thumbnailUrl = it.thumbnailUrl
            )
        }.toList()

        return DirectorIdDto(
            idx = director.idx,
            name = director.name,
            profileUrl = director.profileUrl,
            movieList = movieListDto
        )
    }

}