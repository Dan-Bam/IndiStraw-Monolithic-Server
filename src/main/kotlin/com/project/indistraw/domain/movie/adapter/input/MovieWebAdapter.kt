package com.project.indistraw.domain.movie.adapter.input

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.request.UpdateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.MovieDetailResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.MoviePagingResponse
import com.project.indistraw.domain.movie.adapter.input.mapper.MovieDataMapper
import com.project.indistraw.domain.movie.application.port.input.CreateMovieUseCase
import com.project.indistraw.domain.movie.application.port.input.MovieDetailUseCase
import com.project.indistraw.domain.movie.application.port.input.MovieListUseCase
import com.project.indistraw.domain.movie.application.port.input.UpdateMovieUseCase
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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
    private val movieListUseCase: MovieListUseCase,
    private val movieDetailUseCase: MovieDetailUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
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

    @GetMapping("{movie_id}")
    fun findMovieDetail(@PathVariable(name = "movie_id") idx: Int): ResponseEntity<MovieDetailResponse> =
        movieDetailUseCase.execute(idx)
            .let { movieDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("{movie_id}")
    fun updateMovie(@PathVariable(name = "movie_id") idx: Int, @RequestBody @Valid updateMovieRequest: UpdateMovieRequest): ResponseEntity<Void> =
        updateMovieUseCase.execute(idx, movieDataMapper.toDto(updateMovieRequest))
            .let { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}