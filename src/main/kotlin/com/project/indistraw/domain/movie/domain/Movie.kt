package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import java.time.LocalDateTime

@AggregateRoot
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val account: Account,
    val actor: List<Int>,
    val director: List<Int>,
    val movieHighLight: List<String>,
    val genre: Genre,
    val clowdTrue: Boolean,
    val createdAt: LocalDateTime
)