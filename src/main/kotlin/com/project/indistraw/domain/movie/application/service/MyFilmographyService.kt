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

@ServiceWithReadOnlyTransaction
class MyFilmographyService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val queryActorPort: QueryActorPort,
    private val queryDirectorPort: QueryDirectorPort
): MyFilmographyUseCase {

    override fun execute(): List<FilmographyDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx) ?: throw AccountNotFoundException()
        println(account.actor?.size)
        println(account.director?.size)
        val actors = account.actor?.map {
            queryActorPort.findByIdOrNull(it) ?: throw ActorNotFoundException()
        }
        val directors = account.director?.map {
            queryDirectorPort.findByIdOrNull(it) ?: throw DirectorNotFoundException()
        }
        val actorFilmographyList = actors!!.map {
            FilmographyDto(
                idx = it.idx,
                profileUrl = it.profileUrl
            )
        }
        println(actorFilmographyList.size)
        val directorFilmographyList = directors!!.map {
            FilmographyDto(
                idx = it.idx,
                profileUrl = it.profileUrl
            )
        }
        println(directorFilmographyList.size)
        return actorFilmographyList + directorFilmographyList
    }

}