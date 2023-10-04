package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.account.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieEntity
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieHistoryRepository: JpaRepository<MovieHistoryEntity, Long> {
    fun findByMovieAndAccount(movie: MovieEntity, account: AccountEntity): MovieHistoryEntity?
    fun findByAccount(account: AccountEntity): List<MovieHistoryEntity>?
}