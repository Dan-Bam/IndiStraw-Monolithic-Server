package com.project.indistraw.domain.movie.application.port.input

interface SearchMovieUseCase {

    fun execute(keyword: String): List<String>

}