package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<MovieEntity, Int> {

    fun findAllByGenre(pageable: Pageable, genre: Genre?): Page<MovieEntity>

}