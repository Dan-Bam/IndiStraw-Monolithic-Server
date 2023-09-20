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

fun String.toGenre() = when(this) {
    "로맨스" -> Genre.Romance
    "액션" -> Genre.Action
    "코미디" -> Genre.Comedy
    "트릴러" -> Genre.Thriller
    "드라마" -> Genre.Drama
    "과학" -> Genre.SF
    "애니매이션" -> Genre.Animation
    "어드벤쳐" -> Genre.Adventure
    "판타지" -> Genre.Fantasy
    else -> null
}
