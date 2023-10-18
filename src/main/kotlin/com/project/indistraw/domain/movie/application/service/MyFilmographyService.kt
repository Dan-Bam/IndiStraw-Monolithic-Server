package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.movie.application.port.input.MyFilmographyUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.FilmographyDto
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.movie.application.exception.ActorNotFoundException
import com.project.indistraw.domain.movie.application.exception.DirectorNotFoundException
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort

@ServiceWithReadOnlyTransaction
class MyFilmographyService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val queryMoviePort: QueryMoviePort
): MyFilmographyUseCase {

    override fun execute(): List<FilmographyDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx) ?: throw AccountNotFoundException()
        val actors = account.actor?.let {
            queryMoviePort.findByActorIn(it) ?: throw ActorNotFoundException()
        }
        val directors = account.director?.let {
            queryMoviePort.findByDirectorIn(it) ?: throw DirectorNotFoundException()
        }
        val actorFilmographyList = actors!!.map {
            FilmographyDto(
                idx = it.idx,
                thumbnailUrl = it.thumbnailUrl
            )
        }
        val directorFilmographyList = directors!!.map {
            FilmographyDto(
                idx = it.idx,
                thumbnailUrl = it.thumbnailUrl
            )
        }
        return actorFilmographyList + directorFilmographyList
    }

}