package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot

@AggregateRoot
data class Actor(
    val id: Int,
    val profileUrl: String,
    val name: String
)