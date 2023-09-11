package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto

interface FindMovieListUseCase {

    fun execute(genre: Genre?): List<MovieDto>

}