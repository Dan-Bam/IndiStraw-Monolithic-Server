package com.project.indistraw.domain.movie.application.port.input

interface DeleteMovieUseCase {

    fun execute(idx: Long)

}