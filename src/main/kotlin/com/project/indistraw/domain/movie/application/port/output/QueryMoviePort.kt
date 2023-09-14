package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.domain.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface QueryMoviePort {

    fun findAll(pageRequest: PageRequest): Page<Movie>
    fun findAllByGenre(pageRequest: PageRequest, genre: Genre?): Page<Movie>

}