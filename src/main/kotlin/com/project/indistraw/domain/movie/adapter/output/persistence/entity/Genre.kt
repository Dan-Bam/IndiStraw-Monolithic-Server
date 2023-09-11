package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate

@Aggregate
enum class Genre {
    All,
    Action,
    Romance,
    Comedy,
    Thriller,
    Drama,
    SF,
    Animation,
    Adventure,
    Fantasy
}