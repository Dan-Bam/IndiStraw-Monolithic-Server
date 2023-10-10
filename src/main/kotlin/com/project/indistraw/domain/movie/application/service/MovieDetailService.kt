package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.application.exception.MovieNotFoundException
import com.project.indistraw.domain.movie.application.port.input.MovieDetailUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.ActorDto
import com.project.indistraw.domain.movie.application.port.input.dto.DirectorDto
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

    override fun execute(idx: Long): MovieDetailDto {
        val movie = queryMoviePort.findById(idx) ?: throw MovieNotFoundException()

        return MovieDetailDto(
            title = movie.title,
            description = movie.description,
            movieUrl = movie.movieUrl,
            thumbnailUrl = movie.thumbnailUrl,
            director = movie.director.map { queryDirectorPort.findByIdOrNull(it)!!.let {
                DirectorDto(
                    idx = it.idx,
                    name = it.name,
                    profileUrl = it.profileUrl
                )
            } },
            actor = movie.actor.map { queryActorPort.findByIdOrNull(it)!!.let {
                ActorDto(
                    idx = it.idx,
                    name = it.name,
                    profileUrl = it.profileUrl
                )
            } },
            movieHighlight = movie.movieHighlight,
            crowdTrue = movie.crowdTrue,
            genre = movie.genre
        )
    }

}