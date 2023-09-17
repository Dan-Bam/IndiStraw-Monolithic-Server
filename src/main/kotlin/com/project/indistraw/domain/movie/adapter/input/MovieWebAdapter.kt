package com.project.indistraw.domain.movie.adapter.input

import com.project.indistraw.domain.movie.adapter.input.data.request.CreateActorRequest
import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieHistoryRequest
import com.project.indistraw.domain.movie.adapter.input.data.request.CreateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.request.UpdateMovieRequest
import com.project.indistraw.domain.movie.adapter.input.data.response.*
import com.project.indistraw.domain.movie.adapter.input.mapper.ActorDataMapper
import com.project.indistraw.domain.movie.adapter.input.mapper.MovieDataMapper
import com.project.indistraw.domain.movie.adapter.input.mapper.MovieHistoryDataMapper
import com.project.indistraw.domain.movie.application.port.input.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
    private val movieHistoryDataMapper: MovieHistoryDataMapper,
    private val actorDataMapper: ActorDataMapper,
    private val createMovieUseCase: CreateMovieUseCase,
    private val movieListUseCase: MovieListUseCase,
    private val movieDetailUseCase: MovieDetailUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val createMovieHistoryUseCase: CreateMovieHistoryUseCase,
    private val movieHistoryListUseCase: MovieHistoryListUseCase,
    private val searchActorUseCase: SearchActorUseCase,
    private val createActorUseCase: CreateActorUseCase,
    private val searchActorIdUseCase: SearchActorIdUseCase
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
    fun findMovieDetail(@PathVariable(name = "movie_id") id: Int): ResponseEntity<MovieDetailResponse> =
        movieDetailUseCase.execute(id)
            .let { movieDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("{movie_id}")
    fun updateMovie(@PathVariable(name = "movie_id") id: Int, @RequestBody @Valid updateMovieRequest: UpdateMovieRequest): ResponseEntity<Void> =
        updateMovieUseCase.execute(id, movieDataMapper.toDto(updateMovieRequest))
            .let { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @DeleteMapping("{movie_id}")
    fun deleteMovie(@PathVariable(name = "movie_id") id: Int): ResponseEntity<Void> =
        deleteMovieUseCase.execute(id)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("history")
    fun createMovieHistory(@RequestBody @Valid createMovieHistoryRequest: CreateMovieHistoryRequest): ResponseEntity<Void> =
        createMovieHistoryUseCase.execute(movieHistoryDataMapper.toDto(createMovieHistoryRequest))
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("history")
    fun findMovieHistoryList(): ResponseEntity<List<MovieHistoryResponse>> =
        movieHistoryListUseCase.execute()
            .map { movieHistoryDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("actor")
    fun findMovieActor(@RequestParam name: String): ResponseEntity<List<ActorResponse>> =
        searchActorUseCase.execute(name)
            ?.map { actorDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping("actor")
    fun createMovieActor(@RequestBody @Valid createActorRequest: CreateActorRequest): ResponseEntity<ActorResponse> =
        createActorUseCase.execute(actorDataMapper.toDto(createActorRequest))
            .let { ResponseEntity.ok(actorDataMapper.toResponse(it)) }

    @GetMapping("actor/{idx}")
    fun findMovieActorById(@PathVariable idx: Int): ResponseEntity<ActorIdResponse> =
        searchActorIdUseCase.execute(idx)
            .let { actorDataMapper.toResponse(it)  }
            .let { ResponseEntity.ok(it) }

}