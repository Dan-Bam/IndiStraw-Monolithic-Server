package com.project.indistraw.domain.movie.adapter.input

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MoviePagingResponse
import com.project.indistraw.domain.movie.adapter.input.mapper.MovieDataMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.application.port.input.CreateMovieUseCase
import com.project.indistraw.domain.movie.application.port.input.MovieListUseCase
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/movie")
class MovieWebAdapter(
    private val movieDataMapper: MovieDataMapper,
    private val createMovieUseCase: CreateMovieUseCase,
    private val movieListUseCase: MovieListUseCase
) {

    @PostMapping
    fun createMovie(@RequestBody @Valid createMovieRequest: CreateMovieRequest): ResponseEntity<Void> =
        createMovieUseCase.execute(movieDataMapper.toDto(createMovieRequest))
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun findMovieList(@PageableDefault(size=10, page = 0) pageable: Pageable, @RequestParam("keyword") genre: String?): ResponseEntity<MoviePagingResponse> =
        movieListUseCase.execute(pageable, genre)
            .let { movieDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}