package com.project.indistraw.domain.movie.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieEntity
import com.project.indistraw.domain.movie.domain.Movie
import org.springframework.stereotype.Component

@Component
class MovieMapper(
    private val accountMapper: AccountMapper
) {

    fun toEntity(domain: Movie): MovieEntity =
        MovieEntity(
            idx = domain.idx,
            title = domain.title,
            description = domain.description,
            movieUrl = domain.movieUrl,
            thumbnailUrl = domain.thumbnailUrl,
            account = accountMapper.toEntity(domain.account),
            movieHighlight = domain.movieHighlight,
            genre = domain.genre,
            createdAt = domain.createdAt,
            actor = domain.actor,
            director = domain.director,
            crowdTrue = domain.crowdTrue
        )

    fun toDomain(entity: MovieEntity?): Movie? =
        entity?.let {
            Movie(
                idx = it.idx,
                title = it.title,
                description = it.description,
                movieUrl = it.movieUrl,
                thumbnailUrl = it.thumbnailUrl,
                account = accountMapper.toDomain(it.account)!!,
                movieHighlight = it.movieHighlight,
                genre = it.genre,
                createdAt = it.createdAt,
                actor = it.actor,
                director = it.director,
                crowdTrue = it.crowdTrue
            )
        }

}