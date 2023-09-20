package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.application.port.input.CreateMovieUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieDto
import com.project.indistraw.domain.movie.application.port.output.CommandMoviePort
import com.project.indistraw.domain.movie.domain.Movie
import java.time.LocalDateTime

@ServiceWithTransaction
class CreateMovieService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val commandMoviePort: CommandMoviePort,
): CreateMovieUseCase {

    override fun execute(dto: CreateMovieDto) {
        val account = accountSecurityPort.getCurrentAccountIdx().let {
            queryAccountPort.findByIdxOrNull(it) ?: throw AccountNotFoundException()
        }

        val movie = dto.let {
            Movie(
                id = 0,
                title = it.title,
                description = it.description,
                movieUrl = it.movieUrl,
                thumbnailUrl = it.thumbnailUrl,
                account = account,
                actor = it.actor,
                director = it.director,
                clowdTrue = it.clowdTrue,
                movieHighLight = it.movieHighlight,
                genre = Genre.All,
                createdAt = LocalDateTime.now()
            )
        }

        commandMoviePort.saveMovie(movie)
    }

}