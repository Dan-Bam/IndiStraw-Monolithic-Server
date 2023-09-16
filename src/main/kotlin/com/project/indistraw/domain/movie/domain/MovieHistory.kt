package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import com.project.indistraw.domain.account.domain.Account

@AggregateRoot
data class MovieHistory(
    val id: Int,
    val movie: Movie,
    val account: Account,
    var historyTime: Float
) {

    fun updateHistory(historyTime: Float): MovieHistory {
        this.historyTime = historyTime
        return this
    }

}