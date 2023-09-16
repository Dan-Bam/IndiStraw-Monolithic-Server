package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieEntity
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieHistoryRepository: JpaRepository<MovieHistoryEntity, Int> {
    fun findByMovie(movie: MovieEntity): MovieHistoryEntity?
}