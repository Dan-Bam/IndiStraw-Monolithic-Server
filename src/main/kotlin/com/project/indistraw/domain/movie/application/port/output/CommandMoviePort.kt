package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.Movie

interface CommandMoviePort {

    fun saveMovie(movie: Movie)
    fun deleteMovie(movie: Movie)

}