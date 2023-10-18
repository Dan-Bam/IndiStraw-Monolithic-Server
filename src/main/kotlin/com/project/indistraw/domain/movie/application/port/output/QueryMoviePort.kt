package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.domain.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface QueryMoviePort {

    fun findAll(pageRequest: PageRequest): Page<Movie>
    fun findByActorContaining(actorIdx: Long): List<Movie>
    fun findByMovieContaining(directorIdx: Long): List<Movie>
    fun findAllByGenre(pageRequest: PageRequest, genre: Genre?): Page<Movie>
    fun findByGenre(genre: Genre): List<Movie>
    fun findById(idx: Long): Movie?
    fun findByTitleContaining(keyword: String): List<String>
    fun findByPageableTitleContaining(pageRequest: PageRequest, keyword: String): Page<Movie>
    fun existsByGenre(genre: Genre?): Boolean
    fun findByActorIn(actor: List<Long>): List<Movie>?
    fun findByDirectorIn(director: List<Long>): List<Movie>?

}