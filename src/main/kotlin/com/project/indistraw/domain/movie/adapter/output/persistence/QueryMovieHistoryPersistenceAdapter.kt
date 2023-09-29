package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieHistoryMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.MovieHistoryRepository
import com.project.indistraw.domain.movie.application.port.output.QueryMovieHistoryPort
import com.project.indistraw.domain.movie.domain.Movie
import com.project.indistraw.domain.movie.domain.MovieHistory
import org.springframework.stereotype.Component

@Component
class QueryMovieHistoryPersistenceAdapter(
    private val movieHistoryMapper: MovieHistoryMapper,
    private val movieMapper: MovieMapper,
    private val accountMapper: AccountMapper,
    private val movieHistoryRepository: MovieHistoryRepository
): QueryMovieHistoryPort {

    override fun findByMovieAndAccount(movie: Movie, account: Account): MovieHistory? {
        val movieHistory = movieHistoryRepository.findByMovieAndAccount(movieMapper.toEntity(movie), accountMapper.toEntity(account))
        return movieHistoryMapper.toDomain(movieHistory)
    }

    override fun findByAccount(account: Account): List<MovieHistory>? {
        val movieHistory = movieHistoryRepository.findByAccount(accountMapper.toEntity(account))
        return movieHistory?.map { movieHistoryMapper.toDomain(it)!! }
    }

}