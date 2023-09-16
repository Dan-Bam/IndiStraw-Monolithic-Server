package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.MovieHistory

interface CommandMovieHistoryPort {

    fun saveMovieHistory(movieHistory: MovieHistory)

}