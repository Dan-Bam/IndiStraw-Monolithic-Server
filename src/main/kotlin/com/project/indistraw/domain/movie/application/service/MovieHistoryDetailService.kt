package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.account.application.port.output.AccountSecurityPort
import com.project.indistraw.domain.movie.application.port.input.MovieHistoryDetailUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.MovieHistoryDetailDto
import com.project.indistraw.domain.movie.application.port.output.QueryMovieHistoryPort

@ServiceWithReadOnlyTransaction
class MovieHistoryDetailService(
    private val queryMovieHistoryPort: QueryMovieHistoryPort,
    private val accountSecurityPort: AccountSecurityPort
): MovieHistoryDetailUseCase {

    override fun execute(idx: Long): MovieHistoryDetailDto {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val movieHistory = queryMovieHistoryPort.findByMovieIdxAndAccount(idx, accountIdx)

        return MovieHistoryDetailDto(
            historyTime = movieHistory?.historyTime ?: 0.0f
        )
    }

}