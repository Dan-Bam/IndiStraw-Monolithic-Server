package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<MovieEntity, Long> {

    fun findByGenre(pageable: Pageable, genre: Genre?): Page<MovieEntity>
    fun findByGenre(genre: Genre): List<MovieEntity>
    fun findByActorContaining(actorIdx: Long): List<MovieEntity>
    fun findByDirectorContaining(directorIdx: Long): List<MovieEntity>
    fun findByTitleContaining(keyword: String): List<MovieEntity>
    fun existsByGenre(genre: Genre?): Boolean

}