package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.Movie
import com.project.indistraw.domain.movie.domain.MovieHistory

interface QueryMovieHistoryPort {

    fun findByMovie(movie: Movie): MovieHistory?

}