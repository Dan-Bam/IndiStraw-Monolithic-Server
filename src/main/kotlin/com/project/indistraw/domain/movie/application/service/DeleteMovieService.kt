package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.movie.application.exception.MovieNotFoundException
import com.project.indistraw.domain.movie.application.port.input.DeleteMovieUseCase
import com.project.indistraw.domain.movie.application.port.output.CommandMoviePort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort

@ServiceWithTransaction
class DeleteMovieService(
    private val commandMoviePort: CommandMoviePort,
    private val queryMoviePort: QueryMoviePort
): DeleteMovieUseCase {

    override fun execute(id: Int) {
        val movie = queryMoviePort.findById(id) ?: throw MovieNotFoundException()
        commandMoviePort.deleteMovie(movie)
    }

}