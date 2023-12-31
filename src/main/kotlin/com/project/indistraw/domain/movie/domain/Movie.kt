package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import java.time.LocalDateTime

@AggregateRoot
data class Movie(
    val idx: Long,
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val account: Account,
    val actor: List<Long>,
    val director: List<Long>,
    val movieHighlight: List<String>,
    val genre: Genre,
    val crowdTrue: Boolean,
    val createdAt: LocalDateTime
)