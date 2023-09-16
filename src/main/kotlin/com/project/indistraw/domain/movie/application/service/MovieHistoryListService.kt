package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.movie.application.exception.MovieNotFoundException
import com.project.indistraw.domain.movie.application.port.input.MovieHistoryListUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDto
import com.project.indistraw.domain.movie.application.port.output.QueryMovieHistoryPort

@ServiceWithReadOnlyTransaction
class MovieHistoryListService(
    private val queryMovieHistoryPort: QueryMovieHistoryPort,
    private val securityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort
): MovieHistoryListUseCase {

    override fun execute(): List<MovieHistoryDto> {
        val accountIdx = securityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx) ?: throw AccountNotFoundException()
        val movie = queryMovieHistoryPort.findByAccount(account) ?: throw MovieNotFoundException()
        return movie.map {
            MovieHistoryDto(
                title = it.movie.title,
                thumbnailUrl = it.movie.thumbnailUrl,
                historyTime = it.historyTime,
                movieIdx = it.movie.id
            )
        }
    }

}