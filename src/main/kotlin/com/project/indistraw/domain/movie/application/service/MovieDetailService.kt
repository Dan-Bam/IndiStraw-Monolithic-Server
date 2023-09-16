package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.application.exception.MovieNotFoundException
import com.project.indistraw.domain.movie.application.port.input.MovieDetailUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDetailDto
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort

@ServiceWithReadOnlyTransaction
class MovieDetailService(
    private val queryMoviePort: QueryMoviePort,
    private val queryActorPort: QueryActorPort,
    private val queryDirectorPort: QueryDirectorPort
): MovieDetailUseCase {

    override fun execute(id: Int): MovieDetailDto {
        val movie = queryMoviePort.findById(id) ?: throw MovieNotFoundException()

        return MovieDetailDto(
            title = movie.title,
            description = movie.description,
            movieUrl = movie.movieUrl,
            thumbnailUrl = movie.thumbnailUrl,
            director = movie.director.map { queryDirectorPort.findById(it) },
            actor = movie.actor.map { queryActorPort.findById(it) },
            movieHighLight = movie.movieHighLight,
            clowdTrue = movie.clowdTrue,
            genre = movie.genre?.map { it.toString() }
        )
    }

}