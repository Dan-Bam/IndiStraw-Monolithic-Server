package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.movie.application.exception.MovieNotFoundException
import com.project.indistraw.domain.movie.application.port.input.UpdateMovieUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.UpdateMovieDto
import com.project.indistraw.domain.movie.application.port.output.CommandMoviePort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import com.project.indistraw.domain.movie.domain.Movie

@ServiceWithTransaction
class UpdateMovieService(
    private val queryMoviePort: QueryMoviePort,
    private val commandMoviePort: CommandMoviePort
): UpdateMovieUseCase {

    override fun execute(idx: Int, updateMovieDto: UpdateMovieDto) {
        val movie = queryMoviePort.findById(idx)
            .let { it ?: throw MovieNotFoundException() }
            .let {
                Movie(
                    idx = it.idx,
                    title = updateMovieDto.title,
                    description = updateMovieDto.description,
                    movieUrl = updateMovieDto.movieUrl,
                    thumbnailUrl = updateMovieDto.thumbnailUrl,
                    account = it.account,
                    actor = updateMovieDto.actor,
                    director = updateMovieDto.director,
                    movieHighLight = updateMovieDto.movieHighlight,
                    genre = it.genre,
                    clowdTrue = updateMovieDto.clowdTrue,
                    createdAt = it.createdAt
                )
            }

        commandMoviePort.saveMovie(movie)
    }

}