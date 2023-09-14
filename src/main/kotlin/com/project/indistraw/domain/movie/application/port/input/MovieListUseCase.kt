package com.project.indistraw.domain.movie.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.MoviePagingDto
import org.springframework.data.domain.Pageable

interface MovieListUseCase {

    fun execute(pageable: Pageable, genre: String?): MoviePagingDto

}