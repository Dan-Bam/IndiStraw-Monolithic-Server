package com.project.indistraw.domain.search.application.port.input

interface SearchMovieUseCase {

    fun execute(keyword: String): List<String>

}