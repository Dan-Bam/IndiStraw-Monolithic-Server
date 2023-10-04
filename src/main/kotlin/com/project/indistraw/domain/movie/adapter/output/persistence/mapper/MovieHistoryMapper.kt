package com.project.indistraw.domain.movie.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieHistoryEntity
import com.project.indistraw.domain.movie.domain.MovieHistory
import org.springframework.stereotype.Component

@Component
class MovieHistoryMapper(
    private val movieMapper: MovieMapper,
    private val accountMapper: AccountMapper
) {

    fun toEntity(domain: MovieHistory): MovieHistoryEntity =
        MovieHistoryEntity(
            idx = domain.idx,
            movie = movieMapper.toEntity(domain.movie),
            account = accountMapper.toEntity(domain.account),
            historyTime = domain.historyTime
        )

    fun toDomain(entity: MovieHistoryEntity?): MovieHistory? =
        entity?.let {
            MovieHistory(
                idx = it.idx,
                movie = movieMapper.toDomain(it.movie)!!,
                account = accountMapper.toDomain(it.account)!!,
                historyTime = it.historyTime
            )
        }

}