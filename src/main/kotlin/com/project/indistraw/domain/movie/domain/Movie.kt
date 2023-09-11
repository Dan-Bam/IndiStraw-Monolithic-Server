package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import com.project.indistraw.domain.account.domain.Account

@AggregateRoot
data class Movie(
    val idx: Int,
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val account: Account,
    val movieHighLight: List<String>
)