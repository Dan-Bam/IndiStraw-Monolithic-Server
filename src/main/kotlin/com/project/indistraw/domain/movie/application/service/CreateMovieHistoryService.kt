package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.account.application.exception.AccountNotFoundException
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.account.application.port.output.QueryAccountPort
import com.project.indistraw.domain.movie.application.exception.MovieNotFoundException
import com.project.indistraw.domain.movie.application.port.input.CreateMovieHistoryUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.CreateMovieHistoryDto
import com.project.indistraw.domain.movie.application.port.output.CommandMovieHistoryPort
import com.project.indistraw.domain.movie.application.port.output.QueryMovieHistoryPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import com.project.indistraw.domain.movie.domain.MovieHistory

@ServiceWithTransaction
class CreateMovieHistoryService(
    private val commandMovieHistoryPort: CommandMovieHistoryPort,
    private val queryMoviePort: QueryMoviePort,
    private val queryMovieHistoryPort: QueryMovieHistoryPort,
    private val securityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort
): CreateMovieHistoryUseCase {

    override fun execute(createMovieHistoryDto: CreateMovieHistoryDto) {
        val accountIdx = securityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx) ?: throw AccountNotFoundException()
        val movie = queryMoviePort.findById(createMovieHistoryDto.movieId) ?: throw MovieNotFoundException()
        val movieHistory = queryMovieHistoryPort.findByMovieAndAccount(movie, account) ?:
            MovieHistory(
                id = 0,
                movie = movie,
                account = account,
                historyTime = createMovieHistoryDto.historyTime
            )

        commandMovieHistoryPort.saveMovieHistory(movieHistory.copy(historyTime = createMovieHistoryDto.historyTime))
    }

}