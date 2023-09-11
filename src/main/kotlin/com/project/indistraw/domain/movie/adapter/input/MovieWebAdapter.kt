package com.project.indistraw.domain.movie.adapter.input

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.mapper.MovieDataMapper
import com.project.indistraw.domain.movie.application.port.input.CreateMovieUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/movie")
class MovieWebAdapter(
    private val movieDataMapper: MovieDataMapper,
    private val createMovieUseCase: CreateMovieUseCase
) {

    @PostMapping
    fun createMovie(@RequestBody @Valid createMovieRequest: CreateMovieRequest): ResponseEntity<Void> =
        createMovieUseCase.execute(movieDataMapper.toDto(createMovieRequest))
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

}