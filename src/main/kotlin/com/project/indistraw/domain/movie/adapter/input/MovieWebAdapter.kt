package com.project.indistraw.domain.movie.adapter.input

import com.project.indistraw.domain.movie.adapter.input.data.request.*
import com.project.indistraw.domain.movie.adapter.input.data.response.*
import com.project.indistraw.domain.movie.adapter.input.mapper.ActorDataMapper
import com.project.indistraw.domain.movie.adapter.input.mapper.DirectorDataMapper
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
    private val directorDataMapper: DirectorDataMapper,
    private val createMovieUseCase: CreateMovieUseCase,
    private val movieListUseCase: MovieListUseCase,
    private val movieDetailUseCase: MovieDetailUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val createMovieHistoryUseCase: CreateMovieHistoryUseCase,
    private val movieHistoryListUseCase: MovieHistoryListUseCase,
    private val movieHistoryDetailUseCase: MovieHistoryDetailUseCase,
    private val searchActorUseCase: SearchActorUseCase,
    private val createActorUseCase: CreateActorUseCase,
    private val searchActorIdUseCase: SearchActorIdUseCase,
    private val searchDirectorUseCase: SearchDirectorUseCase,
    private val createDirectorUseCase: CreateDirectorUseCase,
    private val searchDirectorIdUseCase: SearchDirectorIdUseCase
) {

    @PostMapping
    fun createMovie(@RequestBody @Valid createMovieRequest: CreateMovieRequest): ResponseEntity<Void> =
        createMovieUseCase.execute(movieDataMapper.toDto(createMovieRequest))
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun findMovieDataList(@PageableDefault(size=10, page = 0) pageable: Pageable, @RequestParam("keyword") genre: String?): ResponseEntity<MoviePagingResponse> =
        movieListUseCase.execute(pageable, genre)
            .let { movieDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("{idx}")
    fun findMovieDetail(@PathVariable(name = "idx") idx: Long): ResponseEntity<MovieDetailResponse> =
        movieDetailUseCase.execute(idx)
            .let { movieDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("{idx}")
    fun updateMovie(@PathVariable(name = "idx") idx: Long, @RequestBody @Valid updateMovieRequest: UpdateMovieRequest): ResponseEntity<Void> =
        updateMovieUseCase.execute(idx, movieDataMapper.toDto(updateMovieRequest))
            .let { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @DeleteMapping("{idx}")
    fun deleteMovie(@PathVariable(name = "idx") idx: Long): ResponseEntity<Void> =
        deleteMovieUseCase.execute(idx)
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

    @GetMapping("history/{idx}")
    fun findMovieHistory(@PathVariable(name = "idx") idx: Long): ResponseEntity<MovieHistoryDetailResponse> =
        movieHistoryDetailUseCase.execute(idx)
            .let { movieHistoryDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("actor")
    fun findMovieActor(@RequestParam name: String): ResponseEntity<List<ActorResponse>> =
        searchActorUseCase.execute(name)
            .map { actorDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping("actor")
    fun createMovieActor(@RequestBody @Valid createActorRequest: CreateActorRequest): ResponseEntity<ActorResponse> =
        createActorUseCase.execute(actorDataMapper.toDto(createActorRequest))
            .let { ResponseEntity.ok(actorDataMapper.toResponse(it)) }

    @GetMapping("actor/{idx}")
    fun findMovieActorById(@PathVariable idx: Long): ResponseEntity<ActorIdResponse> =
        searchActorIdUseCase.execute(idx)
            .let { actorDataMapper.toResponse(it)  }
            .let { ResponseEntity.ok(it) }

    @GetMapping("director")
    fun findMovieDirector(@RequestParam name: String): ResponseEntity<List<DirectorResponse>> =
        searchDirectorUseCase.execute(name)
            .map { directorDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping("director")
    fun createMovieDirector(@RequestBody @Valid createDirectorRequest: CreateDirectorRequest): ResponseEntity<DirectorResponse> =
        createDirectorUseCase.execute(directorDataMapper.toDto(createDirectorRequest))
            .let { ResponseEntity.ok(directorDataMapper.toResponse(it)) }

    @GetMapping("director/{idx}")
    fun findMovieDirectorById(@PathVariable idx: Long): ResponseEntity<DirectorIdResponse> =
        searchDirectorIdUseCase.execute(idx)
            .let { directorDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}