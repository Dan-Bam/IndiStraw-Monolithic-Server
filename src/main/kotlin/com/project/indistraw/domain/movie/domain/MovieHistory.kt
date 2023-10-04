package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import com.project.indistraw.domain.account.domain.Account

@AggregateRoot
data class MovieHistory(
    val idx: Long,
    val movie: Movie,
    val account: Account,
    var historyTime: Float
)