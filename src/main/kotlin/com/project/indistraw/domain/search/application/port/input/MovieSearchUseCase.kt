package com.project.indistraw.domain.search.application.port.input

import com.project.indistraw.domain.movie.application.port.input.dto.MoviePagingDto
import org.springframework.data.domain.Pageable

interface MovieSearchUseCase {

    fun execute(pageable: Pageable, keyword: String): MoviePagingDto

}