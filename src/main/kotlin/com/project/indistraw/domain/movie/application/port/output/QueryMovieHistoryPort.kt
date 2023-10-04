package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.movie.domain.Movie
import com.project.indistraw.domain.movie.domain.MovieHistory
import java.util.UUID

interface QueryMovieHistoryPort {

    fun findByMovieAndAccount(movie: Movie, account: Account): MovieHistory?
    fun findByAccount(account: Account): List<MovieHistory>?
    fun findByMovieIdxAndAccount(idx: Long, accountIdx: UUID): MovieHistory?

}