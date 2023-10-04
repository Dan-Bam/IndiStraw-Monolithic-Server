package com.project.indistraw.domain.movie.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot

@AggregateRoot
data class Director(
    val idx: Long,
    val profileUrl: String,
    val name: String
)